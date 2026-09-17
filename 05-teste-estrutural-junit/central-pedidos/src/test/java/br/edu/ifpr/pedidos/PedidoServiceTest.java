package br.edu.ifpr.pedidos;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PedidoServiceTest {

    @Test
    void deveFecharPedidoDeClienteComumComFreteDoParanaEPagamentoAprovado() {
        Cliente cliente = new Cliente(false, false, 1);
        ItemPedido item = new ItemPedido("LIVRO-JAVA", 10_000, 1, 5, 1_000, false);
        Pedido pedido = new Pedido(List.of(item), "PR", false, null);

        List<Long> cobrancas = new ArrayList<>();
        PedidoService service = new PedidoService(total -> {
            cobrancas.add(total);
            return true;
        });

        ResultadoPedido resultado = service.fechar(pedido, cliente);

        assertAll(
            () -> assertEquals("PAGO", resultado.status()),
            () -> assertEquals(10_000L, resultado.subtotalCentavos()),
            () -> assertEquals(0L, resultado.descontoCentavos()),
            () -> assertEquals(1_200L, resultado.freteCentavos()),
            () -> assertEquals(11_200L, resultado.totalCentavos()),
            () -> assertEquals(List.of(11_200L), cobrancas)
        );
    }

    @Test
    void clienteBloqueadoRetornaSemCobranca() {
        Cliente cliente = new Cliente(false, true, 1);
        ItemPedido item = new ItemPedido("A", 5_000, 1, 5, 500, false);
        Pedido pedido = new Pedido(List.of(item), "PR", false, null);
        List<Long> cobrancas = new ArrayList<>();
        PedidoService service = new PedidoService(t -> { cobrancas.add(t); return true; });

        ResultadoPedido r = service.fechar(pedido, cliente);
        assertEquals("BLOQUEADO", r.status());
        assertEquals(0L, r.totalCentavos());
        assertTrue(cobrancas.isEmpty());
    }

    @Test
    void pedidoSemItensAtivosLancaExcecao() {
        Cliente cliente = new Cliente(false, false, 1);
        ItemPedido inativo = new ItemPedido("A", 5_000, 0, 5, 500, false);
        Pedido pedido = new Pedido(List.of(inativo), "PR", false, null);
        PedidoService service = new PedidoService(t -> true);

        assertThrows(IllegalArgumentException.class, () -> service.fechar(pedido, cliente));
    }

    @Test
    void semEstoqueRetornaSemCobranca() {
        Cliente cliente = new Cliente(false, false, 1);
        ItemPedido item = new ItemPedido("A", 5_000, 3, 1, 500, false);
        Pedido pedido = new Pedido(List.of(item), "PR", false, null);
        PedidoService service = new PedidoService(t -> true);

        ResultadoPedido r = service.fechar(pedido, cliente);
        assertEquals("SEM_ESTOQUE", r.status());
        assertEquals(0L, r.totalCentavos());
    }

    @Test
    void pagamentoRecusado() {
        Cliente cliente = new Cliente(false, false, 1);
        ItemPedido item = new ItemPedido("A", 10_000, 1, 5, 500, false);
        Pedido pedido = new Pedido(List.of(item), "PR", false, null);
        PedidoService service = new PedidoService(t -> false);

        ResultadoPedido r = service.fechar(pedido, cliente);
        assertEquals("PAGAMENTO_RECUSADO", r.status());
        assertEquals(11_200L, r.totalCentavos()); // ainda calcula total
    }

    @Test
    void riscoRevisaoNaoCobra() {
        // primeira compra + total alto → REVISAO
        Cliente cliente = new Cliente(false, false, 0);
        ItemPedido item = new ItemPedido("A", 120_000, 1, 5, 500, false);
        Pedido pedido = new Pedido(List.of(item), "PR", false, null);
        List<Long> cobrancas = new ArrayList<>();
        PedidoService service = new PedidoService(t -> { cobrancas.add(t); return true; });

        ResultadoPedido r = service.fechar(pedido, cliente);
        assertEquals("REVISAO", r.status());
        assertTrue(cobrancas.isEmpty());
    }

    @Test
    void vipComCupomEFreteGratis() {
        Cliente vip = new Cliente(true, false, 2);
        ItemPedido item = new ItemPedido("A", 40_000, 1, 5, 500, false);
        Pedido pedido = new Pedido(List.of(item), "PR", false, "EXTRA10");
        PedidoService service = new PedidoService(t -> true);

        ResultadoPedido r = service.fechar(pedido, vip);
        // VIP 10% + EXTRA10 10% = 20% teto → desconto 8_000; liquido 32_000 ≥ 30_000 → frete 0
        assertEquals("PAGO", r.status());
        assertEquals(40_000L, r.subtotalCentavos());
        assertEquals(8_000L, r.descontoCentavos());
        assertEquals(0L, r.freteCentavos());
        assertEquals(32_000L, r.totalCentavos());
    }
}
