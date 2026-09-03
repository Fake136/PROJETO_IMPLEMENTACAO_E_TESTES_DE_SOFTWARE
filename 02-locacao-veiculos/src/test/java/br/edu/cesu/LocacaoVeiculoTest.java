package br.edu.cesu;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Cálculo do valor de locação de veículo")
class LocacaoVeiculoTest {

    private LocacaoVeiculo locacao;

    @BeforeEach
    void setUp() {
        locacao = new LocacaoVeiculo();
    }

    @Nested
    @DisplayName("Fluxos válidos")
    class FluxosValidos {

        @Test
        @DisplayName("Exemplo do enunciado: 10 dias, diária 120, 1200 km, seguro, idade 30 → 1590.00")
        void exemploEnunciado() {
            double resultado = locacao.calcularValorLocacao(10, 120.0, 1200, true, 30);
            assertEquals(1590.00, resultado, 0.01);
        }

        @Test
        @DisplayName("Até 6 dias: sem desconto, sem seguro, sem excedente, idade >= 21")
        void ateSeisDiasSemExtras() {
            // 5 * 100 = 500; franquia 500; km 400 → 0 excedente; seguro 0; idade ok
            double resultado = locacao.calcularValorLocacao(5, 100.0, 400, false, 25);
            assertEquals(500.00, resultado, 0.01);
        }

        @Test
        @DisplayName("7 dias: desconto 5% sobre diárias")
        void seteDiasComDesconto5() {
            // 7 * 100 = 700; desconto 35; = 665; sem demais extras
            double resultado = locacao.calcularValorLocacao(7, 100.0, 700, false, 30);
            assertEquals(665.00, resultado, 0.01);
        }

        @Test
        @DisplayName("15 dias: desconto 10% sobre diárias")
        void quinzeDiasComDesconto10() {
            // 15 * 100 = 1500; desconto 150; = 1350
            double resultado = locacao.calcularValorLocacao(15, 100.0, 1500, false, 30);
            assertEquals(1350.00, resultado, 0.01);
        }

        @Test
        @DisplayName("Com seguro: adiciona 35 por dia")
        void comSeguro() {
            // 3 * 100 = 300; seguro 105; total 405
            double resultado = locacao.calcularValorLocacao(3, 100.0, 300, true, 30);
            assertEquals(405.00, resultado, 0.01);
        }

        @Test
        @DisplayName("Km excedente: 0,50 por km acima da franquia")
        void comKmExcedente() {
            // 3 dias → franquia 300; percorreu 340 → 40 * 0,50 = 20; diárias 300; total 320
            double resultado = locacao.calcularValorLocacao(3, 100.0, 340, false, 30);
            assertEquals(320.00, resultado, 0.01);
        }

        @Test
        @DisplayName("Idade < 21: taxa adicional de 150")
        void idadeMenorQue21() {
            // 2 * 100 = 200; taxa 150; total 350
            double resultado = locacao.calcularValorLocacao(2, 100.0, 200, false, 20);
            assertEquals(350.00, resultado, 0.01);
        }

        @Test
        @DisplayName("Idade exatamente 21: sem taxa")
        void idadeIgualA21() {
            double resultado = locacao.calcularValorLocacao(2, 100.0, 200, false, 21);
            assertEquals(200.00, resultado, 0.01);
        }

        @Test
        @DisplayName("Km exatamente na franquia: sem excedente")
        void kmNaFranquia() {
            double resultado = locacao.calcularValorLocacao(2, 100.0, 200, false, 30);
            assertEquals(200.00, resultado, 0.01);
        }
    }

    @Nested
    @DisplayName("Entradas inválidas (R6)")
    class EntradasInvalidas {

        @Test
        @DisplayName("dias <= 0 lança exceção")
        void diasZeroOuNegativo() {
            assertThrows(IllegalArgumentException.class,
                    () -> locacao.calcularValorLocacao(0, 100.0, 100, false, 30));
            assertThrows(IllegalArgumentException.class,
                    () -> locacao.calcularValorLocacao(-1, 100.0, 100, false, 30));
        }

        @Test
        @DisplayName("valor da diária <= 0 lança exceção")
        void diariaZeroOuNegativa() {
            assertThrows(IllegalArgumentException.class,
                    () -> locacao.calcularValorLocacao(5, 0.0, 100, false, 30));
            assertThrows(IllegalArgumentException.class,
                    () -> locacao.calcularValorLocacao(5, -10.0, 100, false, 30));
        }

        @Test
        @DisplayName("idade <= 17 lança exceção")
        void idadeMenorOuIgual17() {
            assertThrows(IllegalArgumentException.class,
                    () -> locacao.calcularValorLocacao(5, 100.0, 100, false, 17));
            assertThrows(IllegalArgumentException.class,
                    () -> locacao.calcularValorLocacao(5, 100.0, 100, false, 16));
        }

        @Test
        @DisplayName("quilometragem negativa lança exceção")
        void kmNegativo() {
            assertThrows(IllegalArgumentException.class,
                    () -> locacao.calcularValorLocacao(5, 100.0, -1, false, 30));
        }
    }

    @Nested
    @DisplayName("Valores-limite de desconto")
    class LimitesDesconto {

        @ParameterizedTest(name = "{0} dias → desconto esperado refletido no total")
        @CsvSource({
                "6, 600.0",   // sem desconto: 6*100
                "7, 665.0",   // 5%: 700 - 35
                "14, 1330.0", // 5%: 1400 - 70
                "15, 1350.0"  // 10%: 1500 - 150
        })
        void limitesDesconto(int dias, double esperado) {
            double resultado = locacao.calcularValorLocacao(dias, 100.0, dias * 100, false, 30);
            assertEquals(esperado, resultado, 0.01);
        }
    }
}
