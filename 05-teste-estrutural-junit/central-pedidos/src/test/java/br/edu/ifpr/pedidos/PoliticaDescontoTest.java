package br.edu.ifpr.pedidos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PoliticaDescontoTest {

    private final PoliticaDesconto politica = new PoliticaDesconto();

    @Test
    void vipRecebeDezPorcento() {
        Cliente vip = new Cliente(true, false, 1);
        assertEquals(10_000L, politica.calcular(vip, 100_000, null));
    }

    @Test
    void comumComSubtotalAltoRecebeCincoPorcento() {
        Cliente comum = new Cliente(false, false, 1);
        assertEquals(2_500L, politica.calcular(comum, 50_000, null));
    }

    @Test
    void comumComSubtotalBaixoRecebeZero() {
        Cliente comum = new Cliente(false, false, 1);
        assertEquals(0L, politica.calcular(comum, 49_999, null));
    }

    @Test
    void cupomBemvindoAdicionaParaPrimeiraCompra() {
        Cliente novo = new Cliente(false, false, 0);
        // subtotal 15_000 → base 0 + BEMVINDO 2_000
        assertEquals(2_000L, politica.calcular(novo, 15_000, "bemvindo"));
    }

    @Test
    void cupomExtra10AdicionaDezPorcento() {
        Cliente comum = new Cliente(false, false, 2);
        // subtotal 30_000 → base 0 + EXTRA10 3_000
        assertEquals(3_000L, politica.calcular(comum, 30_000, " EXTRA10 "));
    }

    @Test
    void descontoNaoUltrapassaTetoDeVintePorcento() {
        Cliente vip = new Cliente(true, false, 0);
        // VIP 10% + EXTRA10 10% = 20% → teto 20%
        long subtotal = 30_000;
        assertEquals(6_000L, politica.calcular(vip, subtotal, "EXTRA10"));
    }

    @Test
    void cupomDesconhecidoLancaExcecao() {
        Cliente c = new Cliente(false, false, 1);
        assertThrows(IllegalArgumentException.class,
                () -> politica.calcular(c, 20_000, "INVALIDO"));
    }

    @Test
    void subtotalNegativoLancaExcecao() {
        Cliente c = new Cliente(false, false, 1);
        assertThrows(IllegalArgumentException.class,
                () -> politica.calcular(c, -1, null));
    }
}
