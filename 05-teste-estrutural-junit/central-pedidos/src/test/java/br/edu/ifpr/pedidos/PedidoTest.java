package br.edu.ifpr.pedidos;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    @Test
    void subtotalIgnoraItensComQuantidadeZero() {
        ItemPedido ativo = new ItemPedido("A", 1_000, 2, 5, 100, false);
        ItemPedido inativo = new ItemPedido("B", 5_000, 0, 5, 100, false);
        Pedido p = new Pedido(List.of(ativo, inativo), "PR", false, null);
        assertEquals(2_000L, p.subtotalCentavos());
    }

    @Test
    void estoqueSuficienteFalseQuandoAlgumItemFalta() {
        ItemPedido ok = new ItemPedido("A", 100, 1, 5, 100, false);
        ItemPedido falta = new ItemPedido("B", 100, 3, 2, 100, false);
        Pedido p = new Pedido(List.of(ok, falta), "PR", false, null);
        assertFalse(p.estoqueSuficiente());
    }

    @Test
    void pesoSomaApenasAtivos() {
        ItemPedido a = new ItemPedido("A", 100, 2, 5, 500, false); // 1000g
        ItemPedido b = new ItemPedido("B", 100, 0, 5, 900, true);   // inativo
        Pedido p = new Pedido(List.of(a, b), "SP", false, null);
        assertEquals(1_000, p.pesoGramas());
        assertFalse(p.temFragil());
    }

    @Test
    void ufInvalidaLancaExcecao() {
        ItemPedido item = new ItemPedido("A", 100, 1, 1, 100, false);
        assertThrows(IllegalArgumentException.class,
                () -> new Pedido(List.of(item), "pr", false, null));
    }
}
