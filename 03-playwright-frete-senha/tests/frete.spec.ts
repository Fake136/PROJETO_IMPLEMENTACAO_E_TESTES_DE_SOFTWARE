import { test, expect } from '@playwright/test';

/**
 * Regras da página de frete:
 * - CEP com 8 dígitos
 * - Valor do pedido > 0 (aceita vírgula ou ponto)
 * - CEP iniciado por 8 → frete R$ 15,00
 * - Demais CEPs → frete R$ 25,00
 * - Pedidos >= R$ 200,00 → frete grátis
 * - Entrada inválida → "Dados inválidos"
 */

const casosValidos = [
  { cep: '80000000', valor: '100', esperado: 'Frete: R$ 15,00', classe: 'CEP inicia com 8, valor < 200' },
  { cep: '10000000', valor: '100', esperado: 'Frete: R$ 25,00', classe: 'CEP não inicia com 8, valor < 200' },
  { cep: '80000000', valor: '200', esperado: 'Frete grátis', classe: 'limite inferior frete grátis' },
  { cep: '10000000', valor: '250', esperado: 'Frete grátis', classe: 'valor acima de 200' },
  { cep: '89999999', valor: '199,99', esperado: 'Frete: R$ 15,00', classe: 'valor logo abaixo de 200' },
  { cep: '01234567', valor: '50.50', esperado: 'Frete: R$ 25,00', classe: 'valor com ponto decimal' },
];

const casosInvalidos = [
  { cep: '123', valor: '100', classe: 'CEP com menos de 8 dígitos' },
  { cep: '123456789', valor: '100', classe: 'CEP com mais de 8 dígitos' },
  { cep: 'abcdefgh', valor: '100', classe: 'CEP não numérico' },
  { cep: '80000000', valor: '0', classe: 'valor zero' },
  { cep: '80000000', valor: '-10', classe: 'valor negativo' },
  { cep: '80000000', valor: '', classe: 'valor vazio' },
  { cep: '', valor: '100', classe: 'CEP vazio' },
  { cep: '80000000', valor: 'abc', classe: 'valor não numérico' },
];

test.describe('Cálculo de frete', () => {
  for (const caso of casosValidos) {
    test(`válido: ${caso.classe}`, async ({ page }) => {
      await page.goto('/frete');
      await page.getByLabel('CEP').fill(caso.cep);
      await page.getByLabel('Valor do pedido').fill(caso.valor);
      await page.getByRole('button', { name: 'Calcular frete' }).click();

      const resultado = page.locator('#resultado');
      await expect(resultado).toBeVisible();
      await expect(resultado).toHaveText(caso.esperado);
      await expect(resultado).toHaveAttribute('role', 'status');
    });
  }

  for (const caso of casosInvalidos) {
    test(`inválido: ${caso.classe}`, async ({ page }) => {
      await page.goto('/frete');
      await page.getByLabel('CEP').fill(caso.cep);
      await page.getByLabel('Valor do pedido').fill(caso.valor);
      await page.getByRole('button', { name: 'Calcular frete' }).click();

      const resultado = page.locator('#resultado');
      await expect(resultado).toBeVisible();
      await expect(resultado).toHaveText('Dados inválidos');
      await expect(resultado).toHaveAttribute('role', 'alert');
    });
  }
});
