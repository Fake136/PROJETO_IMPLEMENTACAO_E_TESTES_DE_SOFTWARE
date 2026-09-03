import { test, expect } from '@playwright/test';

const URL = 'https://opensource-demo.orangehrmlive.com/';
const USER = 'Admin';
const PASS = 'admin123';

test.describe('OrangeHRM Demo', () => {
  test('login válido + logout', async ({ page }) => {
    await page.goto(URL);

    await page.locator('input[name="username"]').fill(USER);
    await page.locator('input[name="password"]').fill(PASS);
    await page.locator('button[type="submit"]').click();

    await expect(page).toHaveURL(/dashboard/i);
    await expect(page.locator('.oxd-topbar-header-breadcrumb')).toBeVisible();

    await page.locator('.oxd-userdropdown-tab').click();
    await page.getByRole('menuitem', { name: 'Logout' }).click();

    await expect(page.locator('input[name="username"]')).toBeVisible();
    await expect(page.locator('button[type="submit"]')).toBeVisible();
  });

  test('login inválido mostra erro', async ({ page }) => {
    await page.goto(URL);

    await page.locator('input[name="username"]').fill('usuario_errado');
    await page.locator('input[name="password"]').fill('senha_errada');
    await page.locator('button[type="submit"]').click();

    const erro = page.locator('.oxd-alert-content-text');
    await expect(erro).toBeVisible();
    await expect(erro).toContainText(/Invalid credentials/i);
    await expect(page.locator('input[name="username"]')).toBeVisible();
  });
});
