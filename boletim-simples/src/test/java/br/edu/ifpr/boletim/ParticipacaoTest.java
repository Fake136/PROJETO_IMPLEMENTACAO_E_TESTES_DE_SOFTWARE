package br.edu.ifpr.boletim;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParticipacaoTest {

    @Test
    void deveCalcularPontos0(){

        Participacao participacao = new Participacao();

        int resultado = participacao.calcularPontos(false,false);

        assertEquals(0, resultado);
    }

    @Test
    void deveCalcularPontos1(){

        Participacao participacao = new Participacao();

        int resultado = participacao.calcularPontos(false,true);

        assertEquals(1, resultado);
    }

    @Test
    void deveCalcularPontos2(){

        Participacao participacao = new Participacao();

        int resultado = participacao.calcularPontos(true,false);

        assertEquals(2, resultado);
    }

    @Test
    void deveCalcularPontos3(){

        Participacao participacao = new Participacao();

        int resultado = participacao.calcularPontos(true,true);

        assertEquals(3, resultado);
    }





    // TODO: criar o objeto, chamar calcularPontos e verificar o resultado.
}

