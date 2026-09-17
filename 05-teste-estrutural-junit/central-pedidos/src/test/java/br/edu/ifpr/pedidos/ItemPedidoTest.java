package br.edu.ifpr.pedidos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ItemPedidoTest {

    @Test
    void deveCalcularTotal() {
        ItemPedido item = new ItemPedido("SKU", 1_500, 3, 10, 500, false);
        assertEquals(4_500L, item.totalCentavos());
    }

    @Test
    void disponivelQuandoQuantidadeMenorOuIgualEstoque() {
        assertTrue(new ItemPedido("A", 100, 2, 2, 100, false).disponivel());
        assertFalse(new ItemPedido("A", 100, 3, 2, 100, false).disponivel());
    }

    @Test
    void skuEmBrancoLancaExcecao() {
        assertThrows(IllegalArgumentException.class,
                () -> new ItemPedido("  ", 100, 1, 1, 100, false));
    }

    @Test
    void precoInvalidoLancaExcecao() {
        assertThrows(IllegalArgumentException.class,
                () -> new ItemPedido("A", 0, 1, 1, 100, false));
    }

    @Test
    void quantidadeInvalidaLancaExcecao() {
        assertThrows(IllegalArgumentException.class,
                () -> new ItemPedido("A", 100, -1, 1, 100, false));
        assertThrows(IllegalArgumentException.class,
                () -> new ItemPedido("A", 100, 101, 1, 100, false));
    }
}
