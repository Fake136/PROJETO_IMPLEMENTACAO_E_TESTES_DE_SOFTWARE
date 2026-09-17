# Exercício 1 — Classificação de pedido (resolução)

## Código

```java
public String classificarPedido(double valor, boolean clienteVip, boolean pagamentoAprovado) {
    double desconto = 0;
    if (valor >= 500) {          // D1
        desconto = 10;
    }
    if (clienteVip) {            // D2
        desconto += 5;
    }
    if (!pagamentoAprovado) {    // D3
        return "PAGAMENTO RECUSADO";
    }
    double valorFinal = valor - (valor * desconto / 100);
    return "PEDIDO APROVADO: " + valorFinal;
}
```

## 1. Blocos básicos

| Bloco | Instruções |
|-------|------------|
| B1 | `desconto = 0` |
| B2 | decisão `valor >= 500` |
| B3 | `desconto = 10` |
| B4 | decisão `clienteVip` |
| B5 | `desconto += 5` |
| B6 | decisão `!pagamentoAprovado` |
| B7 | `return "PAGAMENTO RECUSADO"` |
| B8 | cálculo `valorFinal` + `return "PEDIDO APROVADO: ..."` |

## 2. Decisões
- **D1:** `valor >= 500` (T/F)
- **D2:** `clienteVip` (T/F)
- **D3:** `!pagamentoAprovado` (T/F)

**Número de decisões = 3**

## 3–5. CFG (nós e arestas)

```
        [B1]
          │
        [B2 D1]
       T/   \F
     [B3]    │
       \    /
        [B4 D2]
       T/   \F
     [B5]    │
       \    /
        [B6 D3]
       T/   \F
     [B7]   [B8]
       │      │
      [FIM]  [FIM]
```

- **N = 8** (B1…B8; FIM pode ser unificado → N≈7–8)
- **E = 10** (cada decisão gera 2 saídas + sequências)

Usando nós: B1, D1, B3, D2, B5, D3, B7, B8, FIM → **N = 9**, **E = 11**

## 6–7. Complexidade ciclomática

```
V(G) = E − N + 2 = 11 − 9 + 2 = 4
V(G) = decisões + 1 = 3 + 1 = 4
```

## 8–10. Base de caminhos independentes e dados de teste

| # | Caminho (decisões) | valor | vip | pag. | Resultado esperado |
|---|--------------------|-------|-----|------|--------------------|
| C1 | D1F, D2F, D3F | 100 | false | true | PEDIDO APROVADO: 100.0 |
| C2 | D1T, D2F, D3F | 500 | false | true | PEDIDO APROVADO: 450.0 |
| C3 | D1F, D2T, D3F | 100 | true | true | PEDIDO APROVADO: 95.0 |
| C4 | D1F, D2F, D3T | 100 | false | false | PAGAMENTO RECUSADO |

(Caminhos adicionais possíveis: D1T+D2T → desconto 15%, etc.)

## Questões

- **Combinações booleanas:** 2³ = 8. Complexidade ciclomática = 4 ≠ 8. A base cobre arestas novas, não todas as combinações.
- **return antecipado:** cria aresta direta para o fim, impedindo B8 quando pagamento é recusado.
- **valorFinal com pagamento recusado:** **não** — o return em D3 impede a execução de B8.
