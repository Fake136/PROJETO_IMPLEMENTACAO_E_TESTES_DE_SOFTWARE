import { test, expect } from '@playwright/test';

const URL = 'https://www.saucedemo.com';
const USER = 'standard_user';
const PASS = 'secret_sauce';

test.describe('SauceDemo', () => {
  test('login válido + logout', async ({ page }) => {
    await page.goto(URL);

    await page.locator('#user-name').fill(USER);
    await page.locator('#password').fill(PASS);
    await page.locator('#login-button').click();

    await expect(page).toHaveURL(/inventory\.html/);
    await expect(page.locator('.title')).toHaveText('Products');

    await page.locator('#react-burger-menu-btn').click();
    await page.locator('#logout_sidebar_link').click();

    await expect(page).toHaveURL(URL + '/');
    await expect(page.locator('#login-button')).toBeVisible();
  });

  test('login inválido mostra erro', async ({ page }) => {
    await page.goto(URL);

    await page.locator('#user-name').fill('usuario_invalido');
    await page.locator('#password').fill('senha_errada');
    await page.locator('#login-button').click();

    const erro = page.locator('[data-test="error"]');
    await expect(erro).toBeVisible();
    await expect(erro).toContainText('Epic sadface');
    await expect(page.locator('#login-button')).toBeVisible();
  });
});
