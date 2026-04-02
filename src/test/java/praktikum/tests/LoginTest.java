package praktikum.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class LoginTest {
    private WebDriver driver;
    private String registeredEmail;
    private String registeredPassword;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        // Автоматическое управление драйвером
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Явное ожидание (10 секунд)
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Генерируем уникальные данные для регистрации
        registeredEmail = "test" + System.currentTimeMillis() + "@test.com";
        registeredPassword = "123456";

        // Шаг 1: Регистрируем пользователя через UI
        driver.get("https://stellarburgers.education-services.ru/register");

        // Ждём, пока загрузится форма регистрации (поле name)
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[text()='Имя']/following-sibling::input")));

        // Заполняем форму регистрации (используем правильные локаторы)
        driver.findElement(By.xpath("//label[text()='Имя']/following-sibling::input"))
                .sendKeys("Test User");
        driver.findElement(By.xpath("//label[text()='Email']/following-sibling::input"))
                .sendKeys(registeredEmail);
        driver.findElement(By.xpath("//label[text()='Пароль']/following-sibling::input"))
                .sendKeys(registeredPassword);

        // Нажимаем кнопку регистрации
        driver.findElement(By.xpath("//button[text()='Зарегистрироваться']")).click();

        // Ждём перехода на страницу логина
        wait.until(ExpectedConditions.urlContains("login"));

        // Небольшая пауза для стабильности
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldLoginFromMainPageButton() {
        // Шаг 2: Открываем главную страницу
        driver.get("https://stellarburgers.education-services.ru/");

        // Ждём загрузки главной страницы
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[text()='Войти в аккаунт']")));

        // Шаг 3: Кликаем по кнопке «Войти в аккаунт»
        driver.findElement(By.xpath("//button[text()='Войти в аккаунт']")).click();

        // Ждём загрузки страницы логина
        wait.until(ExpectedConditions.urlContains("login"));

        // Шаг 4: Заполняем email и пароль
        driver.findElement(By.xpath("//label[text()='Email']/following-sibling::input"))
                .sendKeys(registeredEmail);
        driver.findElement(By.xpath("//label[text()='Пароль']/following-sibling::input"))
                .sendKeys(registeredPassword);

        // Шаг 5: Нажимаем кнопку «Войти»
        driver.findElement(By.xpath("//button[text()='Войти']")).click();

        // Шаг 6: Ждём появления кнопки «Оформить заказ» и проверяем
        WebElement orderButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[text()='Оформить заказ']")));

        assertTrue("Не удалось войти через кнопку на главной", orderButton.isDisplayed());
    }

    @Test
    public void shouldLoginFromPersonalAccountButton() {
        // Открываем главную страницу
        driver.get("https://stellarburgers.education-services.ru/");

        // Ждём загрузки
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Личный Кабинет']")));

        // Кликаем по кнопке «Личный кабинет»
        driver.findElement(By.xpath("//p[text()='Личный Кабинет']")).click();

        // Ждём страницу логина
        wait.until(ExpectedConditions.urlContains("login"));

        // Заполняем форму входа
        driver.findElement(By.xpath("//label[text()='Email']/following-sibling::input"))
                .sendKeys(registeredEmail);
        driver.findElement(By.xpath("//label[text()='Пароль']/following-sibling::input"))
                .sendKeys(registeredPassword);

        // Нажимаем «Войти»
        driver.findElement(By.xpath("//button[text()='Войти']")).click();

        // Проверяем успешный вход
        WebElement orderButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[text()='Оформить заказ']")));

        assertTrue("Не удалось войти через личный кабинет", orderButton.isDisplayed());
    }

    @Test
    public void shouldLoginFromRegistrationPageLink() {
        // Открываем страницу регистрации
        driver.get("https://stellarburgers.education-services.ru/register");

        // Ждём загрузки и кликаем по ссылке «Войти»
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Войти']")));
        driver.findElement(By.xpath("//a[text()='Войти']")).click();

        // Ждём страницу логина
        wait.until(ExpectedConditions.urlContains("login"));

        // Заполняем форму входа
        driver.findElement(By.xpath("//label[text()='Email']/following-sibling::input"))
                .sendKeys(registeredEmail);
        driver.findElement(By.xpath("//label[text()='Пароль']/following-sibling::input"))
                .sendKeys(registeredPassword);

        // Нажимаем «Войти»
        driver.findElement(By.xpath("//button[text()='Войти']")).click();

        // Проверяем успешный вход
        WebElement orderButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[text()='Оформить заказ']")));

        assertTrue("Не удалось войти через ссылку на странице регистрации", orderButton.isDisplayed());
    }

    @Test
    public void shouldLoginFromForgotPasswordPageLink() {
        // Открываем страницу восстановления пароля
        driver.get("https://stellarburgers.education-services.ru/forgot-password");

        // Ждём загрузки и кликаем по ссылке «Войти»
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Войти']")));
        driver.findElement(By.xpath("//a[text()='Войти']")).click();

        // Ждём страницу логина
        wait.until(ExpectedConditions.urlContains("login"));

        // Заполняем форму входа
        driver.findElement(By.xpath("//label[text()='Email']/following-sibling::input"))
                .sendKeys(registeredEmail);
        driver.findElement(By.xpath("//label[text()='Пароль']/following-sibling::input"))
                .sendKeys(registeredPassword);

        // Нажимаем «Войти»
        driver.findElement(By.xpath("//button[text()='Войти']")).click();

        // Проверяем успешный вход
        WebElement orderButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[text()='Оформить заказ']")));

        assertTrue("Не удалось войти через ссылку на странице восстановления пароля", orderButton.isDisplayed());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}