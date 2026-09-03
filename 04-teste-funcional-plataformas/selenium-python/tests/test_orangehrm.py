from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from webdriver_manager.chrome import ChromeDriverManager
import pytest

URL = "https://opensource-demo.orangehrmlive.com/"
USER = "Admin"
PASS = "admin123"


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
    driver.find_element(By.NAME, "username").send_keys(USER)
    driver.find_element(By.NAME, "password").send_keys(PASS)
    driver.find_element(By.CSS_SELECTOR, "button[type='submit']").click()

    WebDriverWait(driver, 15).until(EC.url_contains("dashboard"))
    assert driver.find_element(By.CLASS_NAME, "oxd-topbar-header-breadcrumb").is_displayed()

    driver.find_element(By.CLASS_NAME, "oxd-userdropdown-tab").click()
    WebDriverWait(driver, 10).until(
        EC.element_to_be_clickable((By.XPATH, "//a[contains(@href,'logout')]"))
    ).click()

    WebDriverWait(driver, 10).until(EC.presence_of_element_located((By.NAME, "username")))
    assert driver.find_element(By.NAME, "username").is_displayed()


def test_login_invalido(driver):
    driver.get(URL)
    driver.find_element(By.NAME, "username").send_keys("usuario_errado")
    driver.find_element(By.NAME, "password").send_keys("senha_errada")
    driver.find_element(By.CSS_SELECTOR, "button[type='submit']").click()

    erro = WebDriverWait(driver, 10).until(
        EC.visibility_of_element_located((By.CLASS_NAME, "oxd-alert-content-text"))
    )
    assert "Invalid credentials" in erro.text
