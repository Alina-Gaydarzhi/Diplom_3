package praktikum.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import praktikum.pages.LoginPage;
import praktikum.pages.RegistrationPage;

import static org.junit.Assert.assertTrue;

public class RegistrationTest {
    private WebDriver driver;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    public void shouldRegisterNewUserSuccessfully() {
        String email = "test" + System.currentTimeMillis() + "@test.com";
        String password = "123456";
        String name = "Test User";

        registrationPage.open();
        registrationPage.waitForPageLoad();
        registrationPage.register(name, email, password);

        loginPage.waitForPageLoad();
        assertTrue("После регистрации не перешли на страницу логина",
                driver.getCurrentUrl().contains("login"));
    }

    @Test
    public void shouldShowErrorForShortPassword() {
        String email = "test@test.com";
        String shortPassword = "12345";
        String name = "Test User";

        registrationPage.open();
        registrationPage.waitForPageLoad();
        registrationPage.register(name, email, shortPassword);

        assertTrue("Сообщение об ошибке пароля не появилось",
                registrationPage.isPasswordErrorDisplayed());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}