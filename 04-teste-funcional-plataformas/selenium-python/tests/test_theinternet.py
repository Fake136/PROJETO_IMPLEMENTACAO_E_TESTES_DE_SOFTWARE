from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from webdriver_manager.chrome import ChromeDriverManager
import pytest

URL = "https://the-internet.herokuapp.com/login"
USER = "tomsmith"
PASS = "SuperSecretPassword!"


@pytest.fixture
def driver():
    options = Options()
    options.add_argument("--headless=new")
    options.add_argument("--no-sandbox")
    options.add_argument("--disable-dev-shm-usage")
    driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()), options=options)
    driver.implicitly_wait(10)
    yield driver
    driver.quit()


def test_login_valido_e_logout(driver):
    driver.get(URL)
    driver.find_element(By.ID, "username").send_keys(USER)
    driver.find_element(By.ID, "password").send_keys(PASS)
    driver.find_element(By.CSS_SELECTOR, "button[type='submit']").click()

    flash = driver.find_element(By.ID, "flash")
    assert "You logged into a secure area!" in flash.text

    driver.find_element(By.LINK_TEXT, "Logout").click()
    flash = driver.find_element(By.ID, "flash")
    assert "You logged out of the secure area!" in flash.text
    assert driver.find_element(By.ID, "username").is_displayed()


def test_login_invalido(driver):
    driver.get(URL)
    driver.find_element(By.ID, "username").send_keys("usuario_errado")
    driver.find_element(By.ID, "password").send_keys("senha_errada")
    driver.find_element(By.CSS_SELECTOR, "button[type='submit']").click()

    flash = driver.find_element(By.ID, "flash")
    assert "Your username is invalid!" in flash.text
