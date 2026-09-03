# Problema da Semana 03 – Locação de Veículos

## Objetivo
Implementar o cálculo do valor final da locação e criar testes automatizados com JUnit 5, seguindo as etapas de uso de IA.

## Estrutura
```
src/main/java/br/edu/cesu/LocacaoVeiculo.java
src/test/java/br/edu/cesu/LocacaoVeiculoTest.java
pom.xml
docs/
  01-entendimento.md
  02-casos-de-teste.md
  03-revisao-critica-ia.md
  04-cobertura.md
```

## Como executar
```bash
mvn test
mvn jacoco:report
# Relatório de cobertura: target/site/jacoco/index.html
```

## Regras implementadas
- R1: valor das diárias
- R2: franquia 100 km/dia + R$ 0,50/km excedente
- R3: seguro R$ 35,00/dia
- R4: desconto 5% (7–14 dias) ou 10% (>14) só sobre diárias
- R5: taxa R$ 150 se idade < 21
- R6: exceções para entradas inválidas
