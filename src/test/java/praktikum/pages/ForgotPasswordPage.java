package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import praktikum.utils.AppConfig;

public class ForgotPasswordPage {
    private final WebDriver driver;

    @FindBy(xpath = "//a[text()='Войти']")
    private WebElement loginLink;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Открыть страницу восстановления пароля")
    public void open() {
        driver.get(AppConfig.FORGOT_PASSWORD_PAGE);
    }

    @Step("Нажать ссылку 'Войти' на странице восстановления")
    public void clickLoginLink() {
        loginLink.click();
    }
}