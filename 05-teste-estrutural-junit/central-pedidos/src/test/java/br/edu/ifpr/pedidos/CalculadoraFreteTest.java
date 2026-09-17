package br.edu.ifpr.pedidos;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculadoraFreteTest {

    private final CalculadoraFrete frete = new CalculadoraFrete();

    private Pedido pedido(String uf, int peso, boolean expresso, boolean fragil) {
        ItemPedido item = new ItemPedido("SKU", 1_000, 1, 10, peso, fragil);
        return new Pedido(List.of(item), uf, expresso, null);
    }

    @Test
    void freteParanaBasico() {
        Cliente c = new Cliente(false, false, 1);
        assertEquals(1_200L, frete.calcular(pedido("PR", 1_000, false, false), c, 10_000));
    }

    @Test
    void freteSpRj() {
        Cliente c = new Cliente(false, false, 1);
        assertEquals(2_000L, frete.calcular(pedido("SP", 1_000, false, false), c, 10_000));
        assertEquals(2_000L, frete.calcular(pedido("RJ", 1_000, false, false), c, 10_000));
    }

    @Test
    void freteOutrasUfs() {
        Cliente c = new Cliente(false, false, 1);
        assertEquals(3_000L, frete.calcular(pedido("MG", 1_000, false, false), c, 10_000));
    }

    @Test
    void pesoExcedenteAdicionaTrezentosPorFaixa() {
        // peso 3500 → excedente 1500 → 2 iterações (1000+500) → +600
        Cliente c = new Cliente(false, false, 1);
        assertEquals(1_800L, frete.calcular(pedido("PR", 3_500, false, false), c, 10_000));
    }

    @Test
    void freteGratisQuandoLiquidoAltoENaoExpresso() {
        Cliente c = new Cliente(false, false, 1);
        assertEquals(0L, frete.calcular(pedido("PR", 1_000, false, false), c, 30_000));
    }

    @Test
    void vipDivideFretePelaMetade() {
        Cliente vip = new Cliente(true, false, 1);
        assertEquals(600L, frete.calcular(pedido("PR", 1_000, false, false), vip, 10_000));
    }

    @Test
    void expressoAdicionaMilEQuinhentos() {
        Cliente c = new Cliente(false, false, 1);
        assertEquals(2_700L, frete.calcular(pedido("PR", 1_000, true, false), c, 10_000));
    }

    @Test
    void fragilAdicionaQuinhentos() {
        Cliente c = new Cliente(false, false, 1);
        assertEquals(1_700L, frete.calcular(pedido("PR", 1_000, false, true), c, 10_000));
    }

    @Test
    void liquidoNegativoLancaExcecao() {
        Cliente c = new Cliente(false, false, 1);
        assertThrows(IllegalArgumentException.class,
                () -> frete.calcular(pedido("PR", 1_000, false, false), c, -1));
    }
}
