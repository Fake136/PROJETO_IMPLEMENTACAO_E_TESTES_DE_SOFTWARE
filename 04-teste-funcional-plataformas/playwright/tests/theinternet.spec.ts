import { test, expect } from '@playwright/test';

const URL = 'https://the-internet.herokuapp.com/login';
const USER = 'tomsmith';
const PASS = 'SuperSecretPassword!';

test.describe('The Internet (Herokuapp)', () => {
  test('login válido + logout', async ({ page }) => {
    await page.goto(URL);

    await page.locator('#username').fill(USER);
    await page.locator('#password').fill(PASS);
    await page.getByRole('button', { name: 'Login' }).click();

    await expect(page.locator('#flash')).toContainText('You logged into a secure area!');
    await expect(page.getByRole('link', { name: 'Logout' })).toBeVisible();

    await page.getByRole('link', { name: 'Logout' }).click();

    await expect(page.locator('#flash')).toContainText('You logged out of the secure area!');
    await expect(page.locator('#username')).toBeVisible();
  });

  test('login inválido mostra erro', async ({ page }) => {
    await page.goto(URL);

    await page.locator('#username').fill('usuario_errado');
    await page.locator('#password').fill('senha_errada');
    await page.getByRole('button', { name: 'Login' }).click();

    await expect(page.locator('#flash')).toContainText('Your username is invalid!');
    await expect(page.locator('#username')).toBeVisible();
  });
});
