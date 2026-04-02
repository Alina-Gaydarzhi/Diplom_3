package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private final WebDriver driver;
    private final HeaderComponent header;
    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By orderButton = By.xpath("//button[text()='Оформить заказ']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.header = new HeaderComponent(driver);
    }

    public void open() {
        driver.get("https://stellarburgers.education-services.ru/");
    }

    public HeaderComponent getHeader() {
        return header;
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public boolean isOrderButtonDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(orderButton));
        return driver.findElement(orderButton).isDisplayed();
    }
}