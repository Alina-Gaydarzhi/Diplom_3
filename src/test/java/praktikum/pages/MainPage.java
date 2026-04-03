package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.utils.AppConfig;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;
    private final HeaderComponent header;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.header = new HeaderComponent(driver);
    }

    private final By loginButton = By.xpath("//button[normalize-space()='Войти в аккаунт']");
    private final By orderButton = By.xpath("//button[normalize-space()='Оформить заказ']");
    private final By mainTitle = By.xpath("//*[contains(text(),'Соберите бургер')]");

    // вкладки
    private final By bunsTab = By.xpath("//span[text()='Булки']/parent::div");
    private final By saucesTab = By.xpath("//span[text()='Соусы']/parent::div");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']/parent::div");

    // секции (для ожиданий)
    private final By bunsSection = By.xpath("//h2[text()='Булки']");
    private final By saucesSection = By.xpath("//h2[text()='Соусы']");
    private final By fillingsSection = By.xpath("//h2[text()='Начинки']");

    @Step("Открыть главную страницу")
    public void open() {
        driver.get(AppConfig.BASE_URL);
        waitVisible(mainTitle, 10);
    }

    public HeaderComponent getHeader() {
        return header;
    }

    @Step("Кликнуть 'Войти в аккаунт'")
    public void clickLoginButton() {
        waitClickable(loginButton, 5).click();
    }

    @Step("Проверить кнопку 'Оформить заказ'")
    public boolean isOrderButtonDisplayed() {
        return waitVisible(orderButton, 5).isDisplayed();
    }

    @Step("Перейти во вкладку 'Булки'")
    public void clickBunsTab() {
        clickTab(bunsTab, bunsSection);
    }

    @Step("Перейти во вкладку 'Соусы'")
    public void clickSaucesTab() {
        clickTab(saucesTab, saucesSection);
    }

    @Step("Перейти во вкладку 'Начинки'")
    public void clickFillingsTab() {
        clickTab(fillingsTab, fillingsSection);
    }

    @Step("Проверить, что активна вкладка 'Булки'")
    public boolean isBunsTabActive() {
        return isTabActive(bunsTab);
    }

    @Step("Проверить, что активна вкладка 'Соусы'")
    public boolean isSaucesTabActive() {
        return isTabActive(saucesTab);
    }

    @Step("Проверить, что активна вкладка 'Начинки'")
    public boolean isFillingsTabActive() {
        return isTabActive(fillingsTab);
    }

    private void clickTab(By tabLocator, By sectionLocator) {
        WebElement tab = waitClickable(tabLocator, 5);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                tab
        );

        tab.click();

        waitVisible(sectionLocator, 5);

        waitForTabActive(tabLocator);
    }

    private void waitForTabActive(By tabLocator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(driver -> {
                    String classAttr = driver.findElement(tabLocator).getAttribute("class");
                    return classAttr != null && classAttr.contains("tab_tab_type_current");
                });
    }

    private boolean isTabActive(By tabLocator) {
        try {
            String classAttr = driver.findElement(tabLocator).getAttribute("class");
            return classAttr != null && classAttr.contains("tab_tab_type_current");
        } catch (Exception e) {
            return false;
        }
    }

    private WebElement waitVisible(By locator, int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private WebElement waitClickable(By locator, int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
}