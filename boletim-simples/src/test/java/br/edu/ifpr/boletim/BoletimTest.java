package br.edu.ifpr.boletim;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoletimTest {

    @Test
    void deveAprovarAlunoComMediaOito() {
        // Preparar: criar o objeto que será testado.
        Boletim boletim = new Boletim();

        // Executar: chamar um único método com uma entrada conhecida.
        String resultado = boletim.verificarSituacao(8);

        // Verificar: comparar o resultado esperado com o resultado obtido.
        assertEquals("APROVADO", resultado);
    }

    @Test
    void deveReprovarAlunoComMediaDois() {
        // Preparar: criar o objeto que será testado.
        Boletim boletim = new Boletim();

        // Executar: chamar um único método com uma entrada conhecida.
        String resultado = boletim.verificarSituacao(2);

        // Verificar: comparar o resultado esperado com o resultado obtido.
        assertEquals("REPROVADO", resultado);
    }

    @Test
    void deveRecuperarAlunoComMediaQuatro() {
        // Preparar: criar o objeto que será testado.
        Boletim boletim = new Boletim();

        // Executar: chamar um único método com uma entrada conhecida.
        String resultado = boletim.verificarSituacao(4);

        // Verificar: comparar o resultado esperado com o resultado obtido.
        assertEquals("RECUPERACAO", resultado);
    }

    @Test
    void deveCalcularMedia(){
        Boletim boletim = new Boletim();
        double resultado = boletim.calcularMedia(8 , 4);
        assertEquals(6, resultado);
    }

    @Test
    void deveContarAprovados(){
        Boletim boletim = new Boletim();
        double quantidade = boletim.contarAprovados(new double[]{5,9,8,6,2,1});
        assertEquals(2, quantidade);
    }


    // TODO: escrever os próximos testes durante a aula.
}
