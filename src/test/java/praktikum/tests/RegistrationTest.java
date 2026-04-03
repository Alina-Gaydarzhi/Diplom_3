package praktikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import praktikum.api.UserCredentials;
import praktikum.base.BaseTest;
import praktikum.pages.MainPage;
import praktikum.pages.LoginPage;
import praktikum.pages.RegistrationPage;
import praktikum.utils.TestUserFactory;

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
    @Description("Проверяет, что пользователь может зарегистрироваться через UI, после чего выполнить вход и увидеть кнопку 'Оформить заказ'")
    public void shouldRegisterNewUserSuccessfully() {
        UserCredentials newUser = TestUserFactory.createRandomUser();

        registrationPage.open();
        registrationPage.register(newUser.getName(), newUser.getEmail(), newUser.getPassword());

        loginPage.waitForPageLoad();

        loginPage.login(newUser.getEmail(), newUser.getPassword());

        // Проверяем, что вход выполнен (отображается кнопка заказа)
        assertTrue("Пользователь не авторизован: кнопка заказа не отображается",
                mainPage.isOrderButtonDisplayed());

        // Получаем токен через API для удаления пользователя после теста
        accessToken = userSteps.loginUser(newUser);
    }

    @Test
    @DisplayName("Ошибка при регистрации с паролем короче 6 символов")
    @Description("Проверяет, что при вводе пароля длиной менее 6 символов появляется сообщение 'Некорректный пароль'")
    public void shouldShowErrorForShortPassword() {
        String shortPassword = "12345";
        UserCredentials user = TestUserFactory.createRandomUser();

        registrationPage.open();
        registrationPage.register(user.getName(), user.getEmail(), shortPassword);

        assertTrue("Сообщение об ошибке пароля не появилось",
                registrationPage.isPasswordErrorDisplayed());
    }
}