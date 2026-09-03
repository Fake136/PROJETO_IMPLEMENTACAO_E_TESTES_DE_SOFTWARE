# Semana 04 – Testes Funcionais com Playwright (Frete + Senha)

Projeto baseado no exemplo da aula, com testes adicionais para as páginas **frete** e **senha**.

## Páginas cobertas
| Página | Spec | Status |
|--------|------|--------|
| Login | `tests/login.spec.ts` | Já existia (exemplo) |
| Idade | `tests/idade.spec.ts` | Já existia (exemplo) |
| **Frete** | `tests/frete.spec.ts` | **Implementado** |
| **Senha** | `tests/senha.spec.ts` | **Implementado** |

## Regras testadas

### Frete
- CEP 8 dígitos
- CEP inicia com 8 → R$ 15,00
- Demais CEPs → R$ 25,00
- Valor ≥ 200 → frete grátis
- Entradas inválidas → "Dados inválidos"

### Senha
- 8 a 20 caracteres
- Maiúscula + minúscula + número
- Sem espaços
- Confirmação deve coincidir

## Como executar

```bash
npm install
npm run browsers
npm test
```

Com navegador visível:
```bash
npm run test:headed
```

Relatório:
```bash
npm run report
```

## Credenciais de login (exemplo didático)
- E-mail: `ana@exemplo.com`
- Senha: `SenhaSegura123!`
