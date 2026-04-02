package praktikum.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import praktikum.api.UserCredentials;
import praktikum.base.BaseTest;
import praktikum.pages.MainPage;
import praktikum.pages.LoginPage;
import praktikum.pages.RegistrationPage;

import static org.junit.Assert.assertTrue;

public class RegistrationTest extends BaseTest {

    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private MainPage mainPage;

    @Before
    public void setUpRegistration() {
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void shouldRegisterNewUserSuccessfully() {
        UserCredentials newUser = userSteps.createRandomUser();

        registrationPage.open();
        registrationPage.register(newUser.getName(), newUser.getEmail(), newUser.getPassword());

        loginPage.waitForPageLoad();
        loginPage.login(newUser.getEmail(), newUser.getPassword());

        assertTrue("Пользователь не авторизован: кнопка заказа не отображается",
                mainPage.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Ошибка при регистрации с паролем короче 6 символов")
    public void shouldShowErrorForShortPassword() {
        String shortPassword = "12345";
        UserCredentials user = userSteps.createRandomUser();

        registrationPage.open();
        registrationPage.register(user.getName(), user.getEmail(), shortPassword);

        assertTrue("Сообщение об ошибке пароля не появилось",
                registrationPage.isPasswordErrorDisplayed());
    }
}