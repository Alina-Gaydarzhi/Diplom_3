package praktikum.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class LoginTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void loginFromMainPage() {
        // Открыть главную
        driver.get("https://stellarburgers.education-services.ru/");

        // Кликнуть по кнопке «Войти в аккаунт»
        driver.findElement(By.xpath("//button[text()='Войти в аккаунт']")).click();

        // Заполнить email и пароль (добавить генерацию)
        driver.findElement(By.xpath("//label[text()='Email']/following-sibling::input"))
                .sendKeys("testuser@example.com");
        driver.findElement(By.xpath("//label[text()='Пароль']/following-sibling::input"))
                .sendKeys("password123");

        // Нажать «Войти»
        driver.findElement(By.xpath("//button[text()='Войти']")).click();

        // Проверить, что появилась кнопка «Оформить заказ»
        boolean orderButtonDisplayed = driver.findElement(By.xpath("//button[text()='Оформить заказ']"))
                .isDisplayed();
        assertTrue("Не удалось войти", orderButtonDisplayed);
    }
}
