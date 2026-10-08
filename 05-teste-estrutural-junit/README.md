# 05 – Teste Estrutural com JUnit

**Atividade da Prova 01 (0,75 ponto)** + projeto avançado.

## Pastas

| Pasta | Descrição |
|-------|-----------|
| `boletim-simples/` | Projeto oficial da SEMANA06 – 4 métodos + testes + JaCoCo (Prova 01) |
| `central-pedidos/` | Projeto avançado com múltiplas classes e testes unitários |

## Como executar o boletim-simples (Prova 01)

```bash
cd 05-teste-estrutural-junit/boletim-simples
mvn clean test
# Abra o relatório de cobertura:
# target/site/jacoco/index.html
```

## Objetivo

- Cobertura de linhas, branches e métodos
- Valores-limite (4 e 7)
- Todas as combinações de `Participacao.calcularPontos`
