package praktikum.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import praktikum.api.UserApi;
import praktikum.api.UserCredentials;
import praktikum.pages.*;

import static org.junit.Assert.assertTrue;

public class LoginTest {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private ForgotPasswordPage forgotPasswordPage;
    private UserApi userApi;
    private String accessToken;
    private UserCredentials testUser;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        userApi = new UserApi();

        // Создаём пользователя через API для всех тестов входа
        String email = "user_" + System.currentTimeMillis() + "@test.com";
        String password = "password123";
        String name = "TestUser";
        testUser = new UserCredentials(email, password, name);
        accessToken = userApi.register(testUser);
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userApi.delete(accessToken);
        }
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void shouldLoginFromMainPageButton() {
        homePage.open();
        homePage.clickLoginButton();

        loginPage.login(testUser.getEmail(), testUser.getPassword());

        assertTrue("Не удалось войти через кнопку на главной",
                homePage.isOrderButtonDisplayed());
    }

    @Test
    public void shouldLoginFromPersonalAccountButton() {
        homePage.open();
        homePage.getHeader().clickPersonalAccount();

        loginPage.login(testUser.getEmail(), testUser.getPassword());

        assertTrue("Не удалось войти через личный кабинет",
                homePage.isOrderButtonDisplayed());
    }

    @Test
    public void shouldLoginFromRegistrationPageLink() {
        registrationPage.open();
        registrationPage.clickLoginLink();

        loginPage.login(testUser.getEmail(), testUser.getPassword());

        assertTrue("Не удалось войти через ссылку на странице регистрации",
                homePage.isOrderButtonDisplayed());
    }

    @Test
    public void shouldLoginFromForgotPasswordPageLink() {
        forgotPasswordPage.open();
        forgotPasswordPage.clickLoginLink();

        loginPage.login(testUser.getEmail(), testUser.getPassword());

        assertTrue("Не удалось войти через ссылку на странице восстановления пароля",
                homePage.isOrderButtonDisplayed());
    }
}