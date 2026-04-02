package praktikum.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class RegistrationTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/register");
    }

    @Test
    public void registerUserSuccessfully() {
        driver.findElement(By.name("name")).sendKeys("Test User");
        driver.findElement(By.name("email")).sendKeys("test" + System.currentTimeMillis() + "@mail.com");
        driver.findElement(By.name("password")).sendKeys("123456");

        driver.findElement(By.xpath("//button[text()='Зарегистрироваться']")).click();

        assertTrue(driver.getCurrentUrl().contains("login"));
    }

    @Test
    public void registerWithShortPassword() {
        driver.findElement(By.name("name")).sendKeys("Test User");
        driver.findElement(By.name("email")).sendKeys("test@mail.com");
        driver.findElement(By.name("password")).sendKeys("123");

        driver.findElement(By.xpath("//button[text()='Зарегистрироваться']")).click();

        WebElement error = driver.findElement(By.xpath("//p[contains(text(),'Некорректный пароль')]"));
        assertTrue(error.isDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
