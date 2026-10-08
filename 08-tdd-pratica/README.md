# 08 – Prática de TDD (Semana 10)

Projetos de Test-Driven Development realizados / baseados nas atividades em sala da SEMANA10.

## Projetos

| Pasta | Descrição |
|-------|-----------|
| `bubblesort-tdd/` | Ordenação Bubble Sort desenvolvida com TDD |
| `carrinho-compras-tdd/` | Carrinho de compras com regras de negócio |
| `conta-bancaria-tdd-maven/` | Conta bancária (depósito, saque, saldo) |
| `estoque-tdd/` | Controle de estoque de produtos |

## Como executar qualquer projeto

```bash
cd 08-tdd-pratica/<nome-do-projeto>
mvn clean test
```

Cada pasta contém:
- `README.md` e/ou `REQUISITOS.md` com as regras
- Código de produção em `src/main/java`
- Testes em `src/test/java`
- `pom.xml` configurado com JUnit 5
