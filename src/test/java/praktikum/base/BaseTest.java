package praktikum.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import praktikum.api.UserApi;
import praktikum.steps.UserSteps;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected UserSteps userSteps;
    protected UserApi userApi;
    protected String accessToken;

    private static final String BASE_URL = "https://stellarburgers.education-services.ru";

    @BeforeClass
    public static void globalSetup() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.filters(
                new RequestLoggingFilter(),
                new ResponseLoggingFilter(),
                new AllureRestAssured()
        );
    }

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        if (browser.equalsIgnoreCase("yandex")) {
            String driverPath = System.getProperty("yandex.driver.path", "C:\\cygwin64\\drivers\\yandexdriver.exe");
            System.setProperty("webdriver.chrome.driver", driverPath);
        } else {
            WebDriverManager.chromedriver().setup();
        }

        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        userSteps = new UserSteps();
        userApi = new UserApi();
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userApi.deleteUser(accessToken);   // Удаление пользователя по токену
        }
        if (userSteps != null) {
            userSteps.deleteUser();            // Удаление пользователя, созданного через API (если был)
        }
        if (driver != null) {
            driver.quit();
        }
    }
}