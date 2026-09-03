import { test, expect } from '@playwright/test';

/**
 * Regras da página de senha:
 * - 8 a 20 caracteres
 * - ao menos 1 maiúscula, 1 minúscula e 1 número
 * - sem espaços
 * - confirmação deve coincidir
 * Mensagens:
 * - "Senha fora do padrão"
 * - "As senhas não coincidem"
 * - "Senha cadastrada"
 */

const senhasValidas = [
  { senha: 'Senha123', classe: 'mínimo 8 caracteres válida' },
  { senha: 'Abcdefg1', classe: 'exatamente 8 caracteres' },
  { senha: 'SenhaSegura12345AB', classe: 'próximo do máximo (18)' },
  { senha: 'A1bcdefghijKLMNopqrs', classe: 'exatamente 20 caracteres' },
];

const senhasInvalidas = [
  { senha: 'Senha1', confirmacao: 'Senha1', msg: 'Senha fora do padrão', classe: 'menos de 8 caracteres' },
  { senha: 'SenhaSemNumero', confirmacao: 'SenhaSemNumero', msg: 'Senha fora do padrão', classe: 'sem número' },
  { senha: 'senhasem1MAIUSC', confirmacao: 'senhasem1MAIUSC', msg: 'Senha fora do padrão', classe: 'sem maiúscula' },
  { senha: 'SENHASEM1minusc', confirmacao: 'SENHASEM1minusc', msg: 'Senha fora do padrão', classe: 'sem minúscula' },
  { senha: 'Senha 123', confirmacao: 'Senha 123', msg: 'Senha fora do padrão', classe: 'com espaço' },
  { senha: 'Abcdefghij12345678901', confirmacao: 'Abcdefghij12345678901', msg: 'Senha fora do padrão', classe: 'mais de 20 caracteres' },
  { senha: 'Senha123', confirmacao: 'Senha456', msg: 'As senhas não coincidem', classe: 'confirmação diferente' },
  { senha: '', confirmacao: '', msg: 'Senha fora do padrão', classe: 'vazia' },
];

test.describe('Cadastro de senha', () => {
  for (const caso of senhasValidas) {
    test(`válida: ${caso.classe}`, async ({ page }) => {
      await page.goto('/senha');
      await page.getByLabel('Nova senha').fill(caso.senha);
      await page.getByLabel('Confirmar senha').fill(caso.senha);
      await page.getByRole('button', { name: 'Cadastrar senha' }).click();

      const resultado = page.locator('#resultado');
      await expect(resultado).toBeVisible();
      await expect(resultado).toHaveText('Senha cadastrada');
      await expect(resultado).toHaveAttribute('role', 'status');
    });
  }

  for (const caso of senhasInvalidas) {
    test(`inválida: ${caso.classe}`, async ({ page }) => {
      await page.goto('/senha');
      await page.getByLabel('Nova senha').fill(caso.senha);
      await page.getByLabel('Confirmar senha').fill(caso.confirmacao);
      await page.getByRole('button', { name: 'Cadastrar senha' }).click();

      const resultado = page.locator('#resultado');
      await expect(resultado).toBeVisible();
      await expect(resultado).toHaveText(caso.msg);
      await expect(resultado).toHaveAttribute('role', 'alert');
    });
  }
});
