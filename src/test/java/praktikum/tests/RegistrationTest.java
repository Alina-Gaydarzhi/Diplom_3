package praktikum.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import praktikum.api.UserApi;
import praktikum.api.UserCredentials;
import praktikum.pages.LoginPage;
import praktikum.pages.RegistrationPage;

import static org.junit.Assert.assertTrue;

public class RegistrationTest {
    private WebDriver driver;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private UserApi userApi;
    private String accessToken;
    private UserCredentials testUser;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
        userApi = new UserApi();

        // Генерируем данные для пользователя (будет использован в UI-регистрации)
        String email = "test" + System.currentTimeMillis() + "@test.com";
        String password = "123456";
        String name = "Test User";
        testUser = new UserCredentials(email, password, name);
    }

    @Test
    public void shouldRegisterNewUserSuccessfully() {
        registrationPage.open();
        registrationPage.waitForPageLoad();
        registrationPage.register(testUser.getName(), testUser.getEmail(), testUser.getPassword());

        loginPage.waitForPageLoad();
        assertTrue("После регистрации не перешли на страницу логина",
                driver.getCurrentUrl().contains("login"));

        // Получаем токен через API для удаления пользователя после теста
        accessToken = userApi.login(testUser);
    }

    @Test
    public void shouldShowErrorForShortPassword() {
        String shortPassword = "12345";

        registrationPage.open();
        registrationPage.waitForPageLoad();
        registrationPage.register(testUser.getName(), testUser.getEmail(), shortPassword);

        assertTrue("Сообщение об ошибке пароля не появилось",
                registrationPage.isPasswordErrorDisplayed());
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
}