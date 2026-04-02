package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderComponent {
    private final WebDriver driver;
    private final By constructorButton = By.xpath("//p[text()='Конструктор']");
    private final By personalAccountButton = By.xpath("//p[text()='Личный Кабинет']");

    public HeaderComponent(WebDriver driver) {
        this.driver = driver;
    }

    public void clickConstructor() {
        driver.findElement(constructorButton).click();
    }

    public void clickPersonalAccount() {
        driver.findElement(personalAccountButton).click();
    }
}