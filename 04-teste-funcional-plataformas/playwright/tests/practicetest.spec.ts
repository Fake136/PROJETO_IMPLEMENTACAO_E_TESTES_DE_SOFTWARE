import { test, expect } from '@playwright/test';

const URL = 'https://practicetestautomation.com/practice-test-login/';
const USER = 'student';
const PASS = 'Password123';

test.describe('Practice Test Automation', () => {
  test('login válido + logout', async ({ page }) => {
    await page.goto(URL);

    await page.locator('#username').fill(USER);
    await page.locator('#password').fill(PASS);
    await page.locator('#submit').click();

    await expect(page).toHaveURL(/logged-in-successfully/);
    await expect(page.getByRole('heading', { name: /Logged In Successfully/i })).toBeVisible();

    await page.getByRole('link', { name: 'Log out' }).click();

    await expect(page).toHaveURL(/practice-test-login/);
    await expect(page.locator('#username')).toBeVisible();
  });

  test('login inválido mostra erro', async ({ page }) => {
    await page.goto(URL);

    await page.locator('#username').fill('usuario_errado');
    await page.locator('#password').fill('senha_errada');
    await page.locator('#submit').click();

    const erro = page.locator('#error');
    await expect(erro).toBeVisible();
    await expect(erro).toContainText(/invalid/i);
    await expect(page.locator('#username')).toBeVisible();
  });
});
