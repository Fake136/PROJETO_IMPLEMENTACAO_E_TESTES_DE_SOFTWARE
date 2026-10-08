# PROJETO, IMPLEMENTAÇÃO E TESTES DE SOFTWARE – 2026

**Aluno:** Heitor Saueressig Mello  
**RA:** 24042002-2  
**Disciplina:** Projeto, Implementação e Teste de Software (2026)  
**Repositório:** https://github.com/Fake136/PROJETO_IMPLEMENTACAO_E_TESTES_DE_SOFTWARE

---

## Entregas da Prova 01 (3,0 pontos)

| Item | Atividade | Pasta | Pontos |
|------|-----------|-------|--------|
| 1 | Plano de Teste + Casos de Teste | [`01-atividade-plano-teste`](./01-atividade-plano-teste) | 0,75 |
| 2 | Teste Funcional – Playwright (frete + senha) | [`03-playwright-frete-senha`](./03-playwright-frete-senha) | 0,75 |
| 3 | Teste Estrutural – JUnit (boletim-simples) | [`05-teste-estrutural-junit/boletim-simples`](./05-teste-estrutural-junit/boletim-simples) | 0,75 |
| 4 | Exercícios GFC – Grafos de Fluxo de Controle | [`06-exercicios-gfc`](./06-exercicios-gfc) | 0,75 |

---

## Estrutura completa do repositório

```
01-atividade-plano-teste/          → Plano + Casos de Teste (Prova 01)
02-locacao-veiculos/               → Extra (Semana 03) – Locação + JUnit
03-playwright-frete-senha/         → Playwright frete + senha (Prova 01)
04-teste-funcional-plataformas/    → Extra – Playwright + Selenium (4 sites)
05-teste-estrutural-junit/         → JUnit + JaCoCo (Prova 01 + central-pedidos)
06-exercicios-gfc/                 → Resoluções GFC (Prova 01)
07-criterios-teste-estrutural/     → Material Semana 09 – Critérios de teste estrutural
08-tdd-pratica/                    → Prática TDD Semana 10 (BubbleSort, Carrinho, Conta, Estoque)
```

---

## Como executar as principais atividades

### 1. Plano e Casos de Teste
Documentos em Markdown na pasta `01-atividade-plano-teste/`.

### 2. Playwright (frete + senha)
```bash
cd 03-playwright-frete-senha
npm install
npx playwright install
npm test
```

### 3. JUnit + JaCoCo (boletim-simples)
```bash
cd 05-teste-estrutural-junit/boletim-simples
mvn clean test
# Relatório: target/site/jacoco/index.html
```

### 4. Exercícios GFC
Arquivos Markdown com resolução completa em `06-exercicios-gfc/`.

### 5. Práticas TDD (Semana 10)
```bash
cd 08-tdd-pratica/bubblesort-tdd   # ou carrinho-compras-tdd, etc.
mvn clean test
```

---

## Observações

- Todas as atividades em sala e de prova foram organizadas por pasta.
- Cada pasta possui seu próprio `README.md` explicando o conteúdo e como executar.
- Arquivos de build (`target/`, `node_modules/`) estão no `.gitignore`.

**Entrega realizada via formulário oficial da disciplina.**
