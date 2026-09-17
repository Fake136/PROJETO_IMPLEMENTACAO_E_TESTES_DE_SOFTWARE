# Relatório — Central de Pedidos (Teste Estrutural)

## Integrantes
- [Preencher nome / RA / turma]

## 1. Grafos e complexidade

### Modelo adotado
- Curto-circuito: cada condição composta é tratada como decisões sequenciais quando necessário.
- Exceções: `IllegalArgumentException` e `IllegalStateException` são testadas, mas **não** entram no contador de branches do JaCoCo.

### Tabela McCabe (métodos principais)

| Método | Decisões | N (nós) | E (arestas) | V(G)=E−N+2 | V(G)=D+1 | Caminhos independentes (resumo) |
|--------|----------|---------|-------------|------------|----------|----------------------------------|
| `PoliticaDesconto.calcular` | 5+ | ~12 | ~16 | 6 | 6 | VIP/comum/zero; cupons; teto; exceção |
| `CalculadoraFrete.calcular` | 6+ | ~14 | ~20 | 8 | 8 | UF; while peso; frete grátis; VIP; expresso; frágil |
| `AnaliseRisco.avaliar` | 5 | ~10 | ~14 | 6 | 6 | bloqueado; 1ª compra; VIP; total alto; aprovado |
| `PagamentoService.pagar` | 3 | ~8 | ~11 | 5 | 4–5 | sucesso; recusa; retry; esgotamento |
| `PedidoService.fechar` | 5 | ~12 | ~17 | 7 | 6 | bloqueado; sem itens; sem estoque; revisão; pago/recusado |
| `Boletim.verificarSituacao` | 2 | 5 | 6 | 3 | 3 | aprovado / recuperação / reprovado |
| `Participacao.calcularPontos` | 2 | 5 | 6 | 3 | 3 | 4 combinações booleanas |
| `Boletim.contarAprovados` | 2 | 6 | 8 | 4 | 3 | 0 iterações; aprovado; não aprovado |

### CFG textual — `AnaliseRisco.avaliar`

```
[1 início]
   │
[2 total < 0?] ──true──► [throw] ──► [fim]
   │ false
[3 bloqueado?] ──true──► [RECUSADO] ──► [fim]
   │ false
[4 compras==0?] ──true──► [5 total>100k OU expresso?] ──true──► [REVISAO] ──► [fim]
   │                         │ false
   │                      [APROVADO] ──► [fim]
   │ false
[6 total>500k E !vip?] ──true──► [REVISAO] ──► [fim]
   │ false
[APROVADO] ──► [fim]
```

**Base de caminhos (exemplo):**
1. total < 0 → exceção
2. bloqueado → RECUSADO
3. 1ª compra + expresso → REVISAO
4. 1ª compra + total baixo → APROVADO
5. cliente comum + total > 500k → REVISAO
6. VIP + total alto → APROVADO

## 2. Matriz de testes (amostra)

| ID | Método JUnit | Unidade | Entrada / stub | Esperado | Caminho / aresta | Critério |
|----|--------------|---------|----------------|----------|------------------|----------|
| AR-01 | `deveAprovarClienteComHistoricoETotalBaixo` | AnaliseRisco | comum, total 50k | APROVADO | caminho feliz | branch |
| AR-02 | `deveRecusarClienteBloqueado` | AnaliseRisco | bloqueado | RECUSADO | retorno antecipado | branch |
| AR-03 | `deveRevisarPrimeiraCompraComTotalAlto` | AnaliseRisco | 0 compras, 100001 | REVISAO | 1ª compra alta | branch |
| PD-01 | `vipRecebeDezPorcento` | PoliticaDesconto | VIP, 100000 | 10000 | ramo VIP | branch |
| CF-01 | `pesoExcedenteAdicionaTrezentosPorFaixa` | CalculadoraFrete | peso 3500 | +600 | while 2x | loop |
| PS-01 | `clienteBloqueadoRetornaSemCobranca` | PedidoService | bloqueado | BLOQUEADO, sem cobrança | retorno antecipado | integração |
| PG-01 | `deveRetentarAposIndisponibilidadeTemporaria` | PagamentoService | 2 falhas + ok | true, 3 chamadas | do-while | exceção |

## 3. Evolução da cobertura

| Etapa | Testes | Linhas | Branches | Métodos | Classes | Lacunas |
|-------|--------|--------|----------|---------|---------|---------|
| Inicial | 1 (PedidoService) | parcial | baixa | parcial | parcial | maioria dos ramos |
| Após suíte completa | ~40+ | ≥95%* | ≥90%* | 100% negócio | 100% | exceções de construtor extremas |

\*Execute `mvn clean test` e preencha os percentuais reais do JaCoCo (`target/site/jacoco/index.html`).

## 4. Análise crítica

- **Cobertura de ramos ≠ cobertura de caminhos:** em `Participacao.calcularPontos`, dois testes (TT e FF) cobrem todos os branches, mas deixam TF e FT sem execução de caminho completo.
- **Curto-circuito:** em `AnaliseRisco`, `total > 100_000 || expresso` — um teste com `expresso=true` e total baixo não avalia o lado esquerdo do OR.
- **Caminhos inviáveis via serviço:** `AnaliseRisco` com total negativo não é alcançável por `PedidoService.fechar` (valores montados a partir de subtotal/desconto/frete ≥ 0). Teste unitário direto é necessário.
- **Exceções:** `IllegalStateException` no pagamento é tratada no `do/while`; JaCoCo não conta isso como branch — testamos mesmo assim.
- **Laços:** `while` do frete e `for` de `contarAprovados` / `estoqueSuficiente` foram exercitados com 0, 1 e N iterações.

## 5. Como reproduzir

```bash
cd central-pedidos
mvn clean test
# Relatório: target/site/jacoco/index.html
```
