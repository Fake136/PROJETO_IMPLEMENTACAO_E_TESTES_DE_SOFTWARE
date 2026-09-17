package br.edu.ifpr.pedidos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AnaliseRiscoTest {

    private final AnaliseRisco risco = new AnaliseRisco();

    @Test
    void deveAprovarClienteComHistoricoETotalBaixo() {
        Cliente c = new Cliente(false, false, 2);
        assertEquals("APROVADO", risco.avaliar(c, 50_000, false));
    }

    @Test
    void deveRecusarClienteBloqueado() {
        Cliente c = new Cliente(false, true, 5);
        assertEquals("RECUSADO", risco.avaliar(c, 10_000, false));
    }

    @Test
    void deveRevisarPrimeiraCompraComTotalAlto() {
        Cliente c = new Cliente(false, false, 0);
        assertEquals("REVISAO", risco.avaliar(c, 100_001, false));
    }

    @Test
    void deveRevisarPrimeiraCompraExpressa() {
        Cliente c = new Cliente(false, false, 0);
        assertEquals("REVISAO", risco.avaliar(c, 1_000, true));
    }

    @Test
    void deveAprovarPrimeiraCompraComTotalBaixoNaoExpresso() {
        Cliente c = new Cliente(false, false, 0);
        assertEquals("APROVADO", risco.avaliar(c, 100_000, false));
    }

    @Test
    void deveRevisarClienteComumComTotalMuitoAlto() {
        Cliente c = new Cliente(false, false, 3);
        assertEquals("REVISAO", risco.avaliar(c, 500_001, false));
    }

    @Test
    void deveAprovarVipComTotalMuitoAlto() {
        Cliente c = new Cliente(true, false, 3);
        assertEquals("APROVADO", risco.avaliar(c, 600_000, false));
    }

    @Test
    void deveLancarExcecaoParaTotalNegativo() {
        Cliente c = new Cliente(false, false, 1);
        assertThrows(IllegalArgumentException.class, () -> risco.avaliar(c, -1, false));
    }
}
