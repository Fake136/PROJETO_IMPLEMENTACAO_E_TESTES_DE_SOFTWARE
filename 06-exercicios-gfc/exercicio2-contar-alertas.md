# Exercício 2 — Contar alertas de temperatura (resolução)

## Código

```java
public int contarAlertas(double[] temperaturas) {
    int alertas = 0;
    int i = 0;
    while (i < temperaturas.length) {   // D1
        if (temperaturas[i] < 0) {      // D2
            alertas += 2;
        } else if (temperaturas[i] > 35) { // D3
            alertas++;
        }
        i++;
    }
    return alertas;
}
```

## 1. Blocos básicos

| Bloco | Conteúdo |
|-------|----------|
| B1 | `alertas=0; i=0` |
| B2 | decisão `i < length` (while) |
| B3 | decisão `temp < 0` |
| B4 | `alertas += 2` |
| B5 | decisão `temp > 35` |
| B6 | `alertas++` |
| B7 | `i++` |
| B8 | `return alertas` |

## 2. Decisões
- **D1:** condição do `while` (`i < length`)
- **D2:** `temperaturas[i] < 0`
- **D3:** `temperaturas[i] > 35` (else-if)

**Decisões = 3 → V(G) mínima = 4**

## 3–5. CFG

```
       [B1 início]
           │
         [B2 D1] ◄──────────────┐
        F/    \T                │
     [B8 return]  [B3 D2]       │
                 T/    \F       │
              [B4]    [B5 D3]   │
                │    T/   \F    │
                │  [B6]    │    │
                │    \    /     │
                \     \  /      │
                 \     \/       │
                  \→ [B7 i++] ──┘
```

- Entrada no laço: aresta T de D1
- Três classificações: <0 | >35 | entre 0 e 35 (cai no falso de D2 e D3)
- Incremento: B7
- Retorno do laço: B7 → B2
- Saída: aresta F de D1 → B8

**N ≈ 8, E ≈ 10**

```
V(G) = E − N + 2 ≈ 10 − 8 + 2 = 4
V(G) = decisões + 1 = 3 + 1 = 4
```

## 7–9. Base de caminhos e vetores de teste

| # | Objetivo | Vetor de entrada | Caminho | Retorno |
|---|----------|------------------|---------|---------|
| C1 | Sair sem iterar | `{}` (vazio) | D1F | 0 |
| C2 | Temperatura negativa | `{-1.0}` | D1T, D2T, B7, D1F | 2 |
| C3 | Temperatura > 35 | `{40.0}` | D1T, D2F, D3T, B7, D1F | 1 |
| C4 | Temperatura entre 0 e 35 | `{20.0}` | D1T, D2F, D3F, B7, D1F | 0 |

**Fronteiras:** `0` e `35` pertencem ao ramo “normal” (não alertam). Valores `-0.1` e `35.1` exercitam os ramos de alerta.

## 10. Por que o retorno do laço precisa aparecer no CFG?
Porque ele cria um **ciclo**. Sem a aresta B7→B2 o grafo seria acíclico e a complexidade (e o número de caminhos independentes) estaria errada. O retorno modela a possibilidade de 0, 1 ou N iterações.

## Questões
- Vetor com várias temperaturas **repete** partes do grafo (o corpo do laço), mas cada combinação de ramos em iterações diferentes ainda se apoia na mesma base de caminhos.
- Entrada que sai sem acessar posição: **array vazio**.
- `else if` é nova decisão porque introduz uma ramificação adicional (T/F) após o primeiro `if` ser falso.
