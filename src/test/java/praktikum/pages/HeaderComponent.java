package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderComponent {
    private final WebDriver driver;

    @FindBy(xpath = "//p[text()='Конструктор']")
    private WebElement constructorLink;

    @FindBy(xpath = "//p[text()='Личный Кабинет']")
    private WebElement personalAccountLink;

    public HeaderComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Кликнуть на 'Конструктор' в шапке")
    public void clickConstructor() {
        constructorLink.click();
    }

    @Step("Кликнуть на 'Личный кабинет' в шапке")
    public void clickPersonalAccount() {
        personalAccountLink.click();
    }
}