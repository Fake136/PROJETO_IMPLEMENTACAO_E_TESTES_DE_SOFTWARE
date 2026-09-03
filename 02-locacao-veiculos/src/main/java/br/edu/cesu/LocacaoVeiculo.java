package br.edu.cesu;

/**
 * Calcula o valor final da locação de um veículo conforme as regras de negócio.
 */
public class LocacaoVeiculo {

    private static final int FRANQUIA_KM_POR_DIA = 100;
    private static final double VALOR_KM_EXCEDENTE = 0.50;
    private static final double VALOR_SEGURO_POR_DIA = 35.00;
    private static final double TAXA_IDADE_MENOR_21 = 150.00;

    /**
     * Calcula o valor final da locação.
     *
     * @param dias                  quantidade de dias (deve ser > 0)
     * @param valorDiaria           valor da diária (deve ser > 0)
     * @param quilometrosPercorridos km percorridos (não pode ser negativo)
     * @param possuiSeguro          se o cliente contratou seguro
     * @param idadeMotorista        idade do motorista (deve ser > 17)
     * @return valor final da locação
     * @throws IllegalArgumentException se alguma entrada for inválida
     */
    public double calcularValorLocacao(
            int dias,
            double valorDiaria,
            int quilometrosPercorridos,
            boolean possuiSeguro,
            int idadeMotorista) {

        validarEntradas(dias, valorDiaria, quilometrosPercorridos, idadeMotorista);

        double valorDiarias = dias * valorDiaria;
        double desconto = calcularDesconto(dias, valorDiarias);
        double valorDiariasComDesconto = valorDiarias - desconto;

        double valorSeguro = possuiSeguro ? dias * VALOR_SEGURO_POR_DIA : 0.0;

        int franquia = dias * FRANQUIA_KM_POR_DIA;
        int excedente = Math.max(0, quilometrosPercorridos - franquia);
        double valorExcedente = excedente * VALOR_KM_EXCEDENTE;

        double taxaIdade = idadeMotorista < 21 ? TAXA_IDADE_MENOR_21 : 0.0;

        return valorDiariasComDesconto + valorSeguro + valorExcedente + taxaIdade;
    }

    private void validarEntradas(int dias, double valorDiaria, int km, int idade) {
        if (dias <= 0) {
            throw new IllegalArgumentException("Dias deve ser maior que zero");
        }
        if (valorDiaria <= 0) {
            throw new IllegalArgumentException("Valor da diária deve ser maior que zero");
        }
        if (idade <= 17) {
            throw new IllegalArgumentException("Idade deve ser maior que 17");
        }
        if (km < 0) {
            throw new IllegalArgumentException("Quilometragem não pode ser negativa");
        }
    }

    private double calcularDesconto(int dias, double valorDiarias) {
        if (dias > 14) {
            return valorDiarias * 0.10;
        }
        if (dias >= 7) {
            return valorDiarias * 0.05;
        }
        return 0.0;
    }
}
