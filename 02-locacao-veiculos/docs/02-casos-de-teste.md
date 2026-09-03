# Etapa 2 – Casos de Teste (sem código)

| ID | Entrada | Resultado esperado | Justificativa |
|----|---------|--------------------|---------------|
| CT01 | 10, 120, 1200, true, 30 | 1590.00 | Exemplo oficial do enunciado |
| CT02 | 5, 100, 400, false, 25 | 500.00 | Até 6 dias, sem extras |
| CT03 | 7, 100, 700, false, 30 | 665.00 | Limite inferior desconto 5% |
| CT04 | 14, 100, 1400, false, 30 | 1330.00 | Limite superior desconto 5% |
| CT05 | 15, 100, 1500, false, 30 | 1350.00 | Limite inferior desconto 10% |
| CT06 | 6, 100, 600, false, 30 | 600.00 | Limite superior sem desconto |
| CT07 | 3, 100, 300, true, 30 | 405.00 | Seguro ativo |
| CT08 | 3, 100, 340, false, 30 | 320.00 | Km excedente (40×0,50) |
| CT09 | 2, 100, 200, false, 20 | 350.00 | Idade < 21 (taxa 150) |
| CT10 | 2, 100, 200, false, 21 | 200.00 | Idade = 21 (sem taxa) |
| CT11 | 2, 100, 200, false, 30 | 200.00 | Km exatamente na franquia |
| CT12 | dias=0 | IllegalArgumentException | Entrada inválida |
| CT13 | valorDiaria=0 | IllegalArgumentException | Entrada inválida |
| CT14 | idade=17 | IllegalArgumentException | Entrada inválida |
| CT15 | km=-1 | IllegalArgumentException | Entrada inválida |
