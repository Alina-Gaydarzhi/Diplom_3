package praktikum.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import praktikum.pages.*;

import static org.junit.Assert.assertTrue;

public class LoginTest {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private ForgotPasswordPage forgotPasswordPage;
    private String registeredEmail;
    private String registeredPassword;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);

        // регистрация
        registeredEmail = "test" + System.currentTimeMillis() + "@test.com";
        registeredPassword = "123456";

        registrationPage.open();
        registrationPage.waitForPageLoad();
        registrationPage.register("Test User", registeredEmail, registeredPassword);
        loginPage.waitForPageLoad(); // после регистрации перешли на логин
    }

    @Test
    public void shouldLoginFromMainPageButton() {
        homePage.open();
        homePage.clickLoginButton();

        loginPage.login(registeredEmail, registeredPassword);

        assertTrue("Не удалось войти через кнопку на главной",
                homePage.isOrderButtonDisplayed());
    }

    @Test
    public void shouldLoginFromPersonalAccountButton() {
        homePage.open();
        homePage.getHeader().clickPersonalAccount();

        loginPage.login(registeredEmail, registeredPassword);

        assertTrue("Не удалось войти через личный кабинет",
                homePage.isOrderButtonDisplayed());
    }

    @Test
    public void shouldLoginFromRegistrationPageLink() {
        registrationPage.open();
        registrationPage.clickLoginLink();

        loginPage.login(registeredEmail, registeredPassword);

        assertTrue("Не удалось войти через ссылку на странице регистрации",
                homePage.isOrderButtonDisplayed());
    }

    @Test
    public void shouldLoginFromForgotPasswordPageLink() {
        forgotPasswordPage.open();
        forgotPasswordPage.clickLoginLink();

        loginPage.login(registeredEmail, registeredPassword);

        assertTrue("Не удалось войти через ссылку на странице восстановления пароля",
                homePage.isOrderButtonDisplayed());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}