package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import praktikum.utils.AppConfig;

public class RegistrationPage {
    private final WebDriver driver;

    @FindBy(xpath = "//label[text()='Имя']/following-sibling::input")
    private WebElement nameField;

    @FindBy(xpath = "//label[text()='Email']/following-sibling::input")
    private WebElement emailField;

    @FindBy(xpath = "//label[text()='Пароль']/following-sibling::input")
    private WebElement passwordField;

    @FindBy(xpath = "//button[text()='Зарегистрироваться']")
    private WebElement registerButton;

    @FindBy(xpath = "//a[text()='Войти']")
    private WebElement loginLink;

    @FindBy(xpath = "//p[text()='Некорректный пароль']")
    private WebElement errorMessage;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Открыть страницу регистрации")
    public void open() {
        driver.get(AppConfig.REGISTER_PAGE);
    }

    @Step("Ввести имя: {name}")
    public void enterName(String name) {
        nameField.sendKeys(name);
    }

    @Step("Ввести email: {email}")
    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    @Step("Ввести пароль: {password}")
    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    @Step("Нажать кнопку 'Зарегистрироваться'")
    public void clickRegisterButton() {
        registerButton.click();
    }

    @Step("Нажать ссылку 'Войти' на странице регистрации")
    public void clickLoginLink() {
        loginLink.click();
    }

    @Step("Зарегистрировать пользователя: {name} / {email} / {password}")
    public void register(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
    }

    @Step("Проверить, что отображается ошибка пароля")
    public boolean isPasswordErrorDisplayed() {
        return errorMessage.isDisplayed();
    }
}