from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from webdriver_manager.chrome import ChromeDriverManager
import pytest

URL = "https://www.saucedemo.com"
USER = "standard_user"
PASS = "secret_sauce"


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
    driver.find_element(By.ID, "user-name").send_keys(USER)
    driver.find_element(By.ID, "password").send_keys(PASS)
    driver.find_element(By.ID, "login-button").click()

    assert "inventory.html" in driver.current_url
    assert driver.find_element(By.CLASS_NAME, "title").text == "Products"

    driver.find_element(By.ID, "react-burger-menu-btn").click()
    driver.find_element(By.ID, "logout_sidebar_link").click()

    assert driver.find_element(By.ID, "login-button").is_displayed()


def test_login_invalido(driver):
    driver.get(URL)
    driver.find_element(By.ID, "user-name").send_keys("usuario_invalido")
    driver.find_element(By.ID, "password").send_keys("senha_errada")
    driver.find_element(By.ID, "login-button").click()

    erro = driver.find_element(By.CSS_SELECTOR, '[data-test="error"]')
    assert erro.is_displayed()
    assert "Epic sadface" in erro.text
