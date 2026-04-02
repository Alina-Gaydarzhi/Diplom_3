package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.utils.AppConfig;
import praktikum.utils.WaitUtils;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final HeaderComponent header;

    @FindBy(xpath = "//button[text()='Войти в аккаунт']")
    private WebElement loginButton;

    @FindBy(xpath = "//button[text()='Оформить заказ']")
    private WebElement orderButton;

    @FindBy(xpath = ".//span[text()='Булки']/..")
    private WebElement bunsTab;

    @FindBy(xpath = ".//span[text()='Соусы']/..")
    private WebElement saucesTab;

    @FindBy(xpath = ".//span[text()='Начинки']/..")
    private WebElement fillingsTab;

    @FindBy(xpath = ".//div[contains(@class, 'tab_tab_type_current')]")
    private WebElement activeTab;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.header = new HeaderComponent(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Открыть главную страницу")
    public void open() {
        driver.get(AppConfig.BASE_URL);
    }

    public HeaderComponent getHeader() {
        return header;
    }

    @Step("Кликнуть на кнопку 'Войти в аккаунт'")
    public void clickLoginButton() {
        loginButton.click();
    }

    @Step("Проверить, что кнопка 'Оформить заказ' отображается")
    public boolean isOrderButtonDisplayed() {
        WaitUtils.waitForElementVisible(driver, orderButton, 5);
        return orderButton.isDisplayed();
    }

    @Step("Кликнуть по вкладке 'Булки'")
    public void clickBunsTab() {
        bunsTab.click();
        waitForTabChange();
    }

    @Step("Кликнуть по вкладке 'Соусы'")
    public void clickSaucesTab() {
        saucesTab.click();
        waitForTabChange();
    }

    @Step("Кликнуть по вкладке 'Начинки'")
    public void clickFillingsTab() {
        fillingsTab.click();
        waitForTabChange();
    }

    @Step("Проверить, что активна вкладка 'Булки'")
    public boolean isBunsTabActive() {
        String classAttribute = bunsTab.getAttribute("class");
        return classAttribute != null && classAttribute.contains("tab_tab_type_current");
    }

    @Step("Проверить, что активна вкладка 'Соусы'")
    public boolean isSaucesTabActive() {
        String classAttribute = saucesTab.getAttribute("class");
        return classAttribute != null && classAttribute.contains("tab_tab_type_current");
    }

    @Step("Проверить, что активна вкладка 'Начинки'")
    public boolean isFillingsTabActive() {
        String classAttribute = fillingsTab.getAttribute("class");
        return classAttribute != null && classAttribute.contains("tab_tab_type_current");
    }

    private void waitForTabChange() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.presenceOfElementLocated(
                        org.openqa.selenium.By.xpath(".//div[contains(@class, 'tab_tab_type_current')]")));
    }
}