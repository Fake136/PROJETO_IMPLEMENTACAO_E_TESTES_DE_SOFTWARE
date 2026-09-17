# Teste estrutural — JUnit (SEMANA 06)

## Projetos

### 1. `boletim-simples/`
Introdução a testes unitários e cobertura.
- Testes completos de `Boletim` (média, situação nos limites 4 e 7, laço com 0/1/N)
- Testes das 4 combinações de `Participacao.calcularPontos`

```bash
cd boletim-simples
mvn test
mvn jacoco:report   # se o plugin estiver no pom
```

### 2. `central-pedidos/`
Laboratório principal de teste estrutural (McCabe + JaCoCo).
- Suíte completa de testes JUnit 5 para todas as classes
- `RELATORIO.md` com CFGs, complexidade, matriz de testes e análise crítica

```bash
cd central-pedidos
mvn clean test
# Abrir target/site/jacoco/index.html
```

**Importante:** não altere o código de produção; apenas os testes e o relatório.
