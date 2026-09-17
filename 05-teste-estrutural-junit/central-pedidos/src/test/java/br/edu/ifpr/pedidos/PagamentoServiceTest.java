package br.edu.ifpr.pedidos;

import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PagamentoServiceTest {

    @Test
    void deveAprovarNaPrimeiraTentativa() {
        PagamentoService svc = new PagamentoService(total -> true);
        assertTrue(svc.pagar(1_000, 3));
    }

    @Test
    void deveRecusarDefinitivamente() {
        PagamentoService svc = new PagamentoService(total -> false);
        assertFalse(svc.pagar(1_000, 3));
    }

    @Test
    void deveRetentarAposIndisponibilidadeTemporaria() {
        AtomicInteger chamadas = new AtomicInteger();
        PagamentoService svc = new PagamentoService(total -> {
            if (chamadas.incrementAndGet() < 3) {
                throw new IllegalStateException("indisponivel");
            }
            return true;
        });
        assertTrue(svc.pagar(1_000, 3));
        assertEquals(3, chamadas.get());
    }

    @Test
    void deveFalharAposEsgotarTentativas() {
        PagamentoService svc = new PagamentoService(total -> {
            throw new IllegalStateException("indisponivel");
        });
        assertFalse(svc.pagar(1_000, 2));
    }

    @Test
    void totalInvalidoLancaExcecao() {
        PagamentoService svc = new PagamentoService(total -> true);
        assertThrows(IllegalArgumentException.class, () -> svc.pagar(0, 1));
    }

    @Test
    void tentativasInvalidasLancaExcecao() {
        PagamentoService svc = new PagamentoService(total -> true);
        assertThrows(IllegalArgumentException.class, () -> svc.pagar(100, 0));
        assertThrows(IllegalArgumentException.class, () -> svc.pagar(100, 4));
    }
}
