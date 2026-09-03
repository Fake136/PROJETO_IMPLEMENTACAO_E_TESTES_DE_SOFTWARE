# Semana 05 – Teste Funcional em 4 Plataformas

Atividade prática: mesmo fluxo de teste implementado em **Playwright (TypeScript)** e **Selenium (Python)**.

## Fluxo-alvo (positivo)
1. Acessar a página de login
2. Fazer login válido
3. Verificar que a página de acesso carregou
4. Fazer logout e confirmar retorno à tela de login

## Fluxo de erro (negativo)
1. Acessar a página de login
2. Fazer login inválido
3. Verificar a mensagem de erro exibida

## Plataformas

| # | Plataforma | URL | Usuário | Senha |
|---|------------|-----|---------|-------|
| 1 | SauceDemo | https://www.saucedemo.com | standard_user | secret_sauce |
| 2 | The Internet | https://the-internet.herokuapp.com/login | tomsmith | SuperSecretPassword! |
| 3 | Practice Test Automation | https://practicetestautomation.com/practice-test-login/ | student | Password123 |
| 4 | OrangeHRM Demo | https://opensource-demo.orangehrmlive.com/ | Admin | admin123 |

## Estrutura
```
playwright/          → testes com Playwright (TypeScript)
selenium-python/     → testes com Selenium (Python)
casos-de-teste.md    → casos de teste funcionais projetados
```

## Como executar

### Playwright
```bash
cd playwright
npm install
npx playwright install chromium
npx playwright test
```

### Selenium Python
```bash
cd selenium-python
python -m venv .venv
source .venv/bin/activate   # Windows: .venv\Scripts\activate
pip install -r requirements.txt
pytest -v
```
