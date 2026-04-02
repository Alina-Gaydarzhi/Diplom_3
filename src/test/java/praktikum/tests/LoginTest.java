package praktikum.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import praktikum.pages.HomePage;
import praktikum.pages.LoginPage;
import praktikum.steps.UserSteps;

import static org.junit.Assert.assertTrue;

public class LoginTest {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private UserSteps userSteps;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        userSteps = new UserSteps();

        // создание пользователя через Steps
        userSteps.createRandomUser();
    }

    @After
    public void tearDown() {
        userSteps.deleteUser();
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void shouldLoginFromMainPage() {
        homePage.open();
        homePage.clickLoginButton();

        loginPage.login(
                userSteps.getCurrentUser().getEmail(),
                userSteps.getCurrentUser().getPassword()
        );

        assertTrue("Не удалось войти", homePage.isOrderButtonDisplayed());
    }
}