package br.edu.ifpr.boletim;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class BoletimTest {

    private final Boletim boletim = new Boletim();

    @Test
    void deveCalcularMediaIgualCinco() {
        assertEquals(5.0, boletim.calcularMedia(5, 5), 0.001);
    }

    @Test
    void deveCalcularMediaComNotasDiferentes() {
        assertEquals(7.5, boletim.calcularMedia(6, 9), 0.001);
    }

    @Test
    void deveCalcularMediaComZero() {
        assertEquals(4.0, boletim.calcularMedia(0, 8), 0.001);
    }

    @ParameterizedTest(name = "media={0} → {1}")
    @CsvSource({
            "10.0, APROVADO",
            "7.0, APROVADO",
            "6.9, RECUPERACAO",
            "4.0, RECUPERACAO",
            "3.9, REPROVADO",
            "0.0, REPROVADO"
    })
    void deveClassificarSituacaoNosLimites(double media, String esperado) {
        assertEquals(esperado, boletim.verificarSituacao(media));
    }

    @Test
    void deveAprovarAlunoComMediaOito() {
        assertEquals("APROVADO", boletim.verificarSituacao(8));
    }

    @Test
    void deveRecuperarNotaAlunoComMediaQuatro() {
        assertEquals("RECUPERACAO", boletim.verificarSituacao(4));
    }

    @Test
    void deveReprovarAlunoComMediaDois() {
        assertEquals("REPROVADO", boletim.verificarSituacao(2));
    }

    @Test
    void deveContarZeroQuandoVetorVazio() {
        assertEquals(0, boletim.contarAprovados(new double[]{}));
    }

    @Test
    void deveContarUmQuandoUnicoAprovado() {
        assertEquals(1, boletim.contarAprovados(new double[]{7.0}));
    }

    @Test
    void deveContarZeroQuandoUnicoReprovado() {
        assertEquals(0, boletim.contarAprovados(new double[]{6.9}));
    }

    @Test
    void deveContarVariosAprovados() {
        assertEquals(3, boletim.contarAprovados(new double[]{8.0, 3.0, 7.0, 5.0, 9.5}));
    }

    @Test
    void deveContarNoLimiteExatoDeSete() {
        assertEquals(2, boletim.contarAprovados(new double[]{7.0, 6.999, 7.001}));
    }
}
