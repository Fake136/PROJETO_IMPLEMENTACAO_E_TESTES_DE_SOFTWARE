package br.edu.ifpr.boletim;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ParticipacaoTest {

    private final Participacao participacao = new Participacao();

    @ParameterizedTest(name = "entregou={0}, participou={1} → {2} pontos")
    @CsvSource({
            "true,  true,  3",
            "true,  false, 2",
            "false, true,  1",
            "false, false, 0"
    })
    void deveCalcularTodasCombinacoes(boolean entregou, boolean participou, int esperado) {
        assertEquals(esperado, participacao.calcularPontos(entregou, participou));
    }

    @Test
    void deveSomarDoisPontosSoComAtividade() {
        assertEquals(2, participacao.calcularPontos(true, false));
    }

    @Test
    void deveSomarUmPontoSoComAula() {
        assertEquals(1, participacao.calcularPontos(false, true));
    }
}
