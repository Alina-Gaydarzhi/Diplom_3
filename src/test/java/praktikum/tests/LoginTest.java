package praktikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import praktikum.base.BaseTest;
import praktikum.pages.*;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private ForgotPasswordPage forgotPasswordPage;

    @Before
    public void setUpLogin() {
        userSteps.createRandomUser();

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
    }

    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт' на главной")
    @Description("Пользователь переходит на главную страницу, нажимает 'Войти в аккаунт', вводит email и пароль, после чего успешно авторизуется")
    public void shouldLoginFromMainPageButton() {
        mainPage.open();
        mainPage.clickLoginButton();

        loginPage.login(
                userSteps.getCurrentUser().getEmail(),
                userSteps.getCurrentUser().getPassword()
        );

        assertTrue("Не удалось войти через кнопку на главной",
                mainPage.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    @Description("Пользователь открывает главную страницу, кликает 'Личный кабинет', вводит данные и успешно входит в систему")
    public void shouldLoginFromPersonalAccountButton() {
        mainPage.open();
        mainPage.getHeader().clickPersonalAccount();

        loginPage.login(
                userSteps.getCurrentUser().getEmail(),
                userSteps.getCurrentUser().getPassword()
        );

        assertTrue("Не удалось войти через личный кабинет",
                mainPage.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через ссылку 'Войти' на странице регистрации")
    @Description("Пользователь открывает страницу регистрации, нажимает ссылку 'Войти', вводит данные и успешно авторизуется")
    public void shouldLoginFromRegistrationPageLink() {
        registrationPage.open();
        registrationPage.clickLoginLink();

        loginPage.login(
                userSteps.getCurrentUser().getEmail(),
                userSteps.getCurrentUser().getPassword()
        );

        assertTrue("Не удалось войти через ссылку на странице регистрации",
                mainPage.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через ссылку 'Войти' на странице восстановления пароля")
    @Description("Пользователь открывает страницу восстановления пароля, нажимает ссылку 'Войти', вводит данные и успешно входит")
    public void shouldLoginFromForgotPasswordPageLink() {
        forgotPasswordPage.open();
        forgotPasswordPage.clickLoginLink();

        loginPage.login(
                userSteps.getCurrentUser().getEmail(),
                userSteps.getCurrentUser().getPassword()
        );

        assertTrue("Не удалось войти через ссылку на странице восстановления пароля",
                mainPage.isOrderButtonDisplayed());
    }
}