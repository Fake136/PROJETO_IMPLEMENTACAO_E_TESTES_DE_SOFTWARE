# Etapa 6 e 7 – Cobertura e Refinamento

## Como medir
```bash
mvn test
mvn jacoco:report
# Abrir: target/site/jacoco/index.html
```

## Meta
- Statement Coverage ≥ 90%
- Branch Coverage ≥ 85%

## Análise esperada
- Todos os ramos de `calcularDesconto` exercitados (0%, 5%, 10%)
- Todos os ramos de validação exercitados
- Ramo de seguro true/false
- Ramo de taxa de idade true/false
- Ramo de km excedente (0 e > 0)

## Refinamento
Se algum ramo ficar vermelho no JaCoCo, adicionar o caso de teste correspondente (já contemplados na suíte atual).
