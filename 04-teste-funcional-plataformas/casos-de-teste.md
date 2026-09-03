# Casos de Teste Funcionais – 4 Plataformas

## CT-POS-01 – Login válido + Logout (fluxo feliz)

| Campo | Conteúdo |
|-------|----------|
| **ID** | CT-POS-01 |
| **Pré-condição** | Navegador aberto; usuário válido da plataforma |
| **Passos** | 1. Acessar URL de login<br>2. Preencher usuário e senha válidos<br>3. Clicar em Login/Submit<br>4. Verificar página autenticada<br>5. Realizar logout<br>6. Verificar retorno à tela de login |
| **Resultado esperado** | Acesso concedido; elementos da área logada visíveis; após logout, formulário de login visível novamente |

Aplicável a: SauceDemo, The Internet, Practice Test Automation, OrangeHRM.

---

## CT-NEG-01 – Login inválido

| Campo | Conteúdo |
|-------|----------|
| **ID** | CT-NEG-01 |
| **Pré-condição** | Navegador aberto |
| **Passos** | 1. Acessar URL de login<br>2. Preencher usuário e/ou senha inválidos<br>3. Clicar em Login/Submit<br>4. Verificar mensagem de erro |
| **Resultado esperado** | Acesso negado; mensagem de erro visível; permanece na tela de login |

Aplicável a: todas as 4 plataformas.

---

## Observações por plataforma

### SauceDemo
- Após login: URL contém `/inventory.html`; título “Products”
- Logout: menu hambúrguer → Logout
- Erro: mensagem “Epic sadface: …”

### The Internet
- Após login: texto “You logged into a secure area!”
- Logout: link “Logout”
- Erro: “Your username is invalid!” ou “Your password is invalid!”

### Practice Test Automation
- Após login: texto “Logged In Successfully”
- Logout: link “Log out”
- Erro: “Your username is invalid!” / “Your password is invalid!”

### OrangeHRM
- Após login: dashboard com “Dashboard” ou “Time at Work”
- Logout: menu do usuário → Logout
- Erro: “Invalid credentials”
