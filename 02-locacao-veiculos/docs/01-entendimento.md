# Etapa 1 – Entendimento do Problema

## Descrição
Sistema de cálculo do valor final de locação de veículo.

## Regras de negócio
1. **Diárias**: dias × valorDiaria
2. **Franquia**: 100 km por dia. Excedente = R$ 0,50/km
3. **Seguro**: R$ 35,00 por dia (se contratado)
4. **Desconto**:
   - até 6 dias → 0%
   - 7 a 14 dias → 5% **somente sobre as diárias**
   - acima de 14 dias → 10% **somente sobre as diárias**
5. **Idade**: < 21 anos → taxa R$ 150,00
6. **Inválidos** (lançam exceção):
   - dias ≤ 0
   - valorDiaria ≤ 0
   - idade ≤ 17
   - km < 0

## Entradas
- dias (int)
- valorDiaria (double)
- quilometrosPercorridos (int)
- possuiSeguro (boolean)
- idadeMotorista (int)

## Saída
- double: valor final da locação
