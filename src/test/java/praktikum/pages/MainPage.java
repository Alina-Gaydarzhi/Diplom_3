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

    private final By loginButton = By.xpath("//button[normalize-space()='Войти в аккаунт']");
    private final By orderButton = By.xpath("//button[normalize-space()='Оформить заказ']");
    private final By mainTitle = By.xpath("//*[contains(normalize-space(.), 'Соберите бургер')]");

    private final By bunsTab = By.xpath("//span[normalize-space()='Булки']/parent::div");
    private final By saucesTab = By.xpath("//span[normalize-space()='Соусы']/parent::div");
    private final By fillingsTab = By.xpath("//span[normalize-space()='Начинки']/parent::div");

    // Локаторы заголовков разделов (по ним проверяем активность)
    private final By bunsSectionTitle = By.xpath("//h2[text()='Булки']");
    private final By saucesSectionTitle = By.xpath("//h2[text()='Соусы']");
    private final By fillingsSectionTitle = By.xpath("//h2[text()='Начинки']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.header = new HeaderComponent(driver);
    }

    @Step("Открыть главную страницу")
    public void open() {
        driver.get(AppConfig.BASE_URL);
        waitForPageLoad();
    }

    @Step("Дождаться загрузки главной страницы")
    public void waitForPageLoad() {
        waitVisible(mainTitle, 10);
    }

    public HeaderComponent getHeader() {
        return header;
    }

    @Step("Кликнуть на кнопку 'Войти в аккаунт'")
    public void clickLoginButton() {
        click(loginButton);
    }

    @Step("Проверить, что кнопка 'Оформить заказ' отображается")
    public boolean isOrderButtonDisplayed() {
        return waitVisible(orderButton, 5).isDisplayed();
    }

    @Step("Кликнуть по вкладке 'Булки'")
    public void clickBunsTab() {
        clickTab(bunsTab);
        waitForBunsSectionVisible();
    }

    @Step("Кликнуть по вкладке 'Соусы'")
    public void clickSaucesTab() {
        clickTab(saucesTab);
        waitForSaucesSectionVisible();
    }

    @Step("Кликнуть по вкладке 'Начинки'")
    public void clickFillingsTab() {
        clickTab(fillingsTab);
        waitForFillingsSectionVisible();
    }

    @Step("Проверить, что активна вкладка 'Булки'")
    public boolean isBunsTabActive() {
        return isElementDisplayed(bunsSectionTitle);
    }

    @Step("Проверить, что активна вкладка 'Соусы'")
    public boolean isSaucesTabActive() {
        return isElementDisplayed(saucesSectionTitle);
    }

    @Step("Проверить, что активна вкладка 'Начинки'")
    public boolean isFillingsTabActive() {
        return isElementDisplayed(fillingsSectionTitle);
    }

    private void clickTab(By tabLocator) {
        WebElement tab = waitClickable(tabLocator, 5);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center', inline: 'center'});",
                tab
        );
        tab.click();
    }

    private void waitForBunsSectionVisible() {
        waitVisible(bunsSectionTitle, 5);
    }

    private void waitForSaucesSectionVisible() {
        waitVisible(saucesSectionTitle, 5);
    }

    private void waitForFillingsSectionVisible() {
        waitVisible(fillingsSectionTitle, 5);
    }

    private boolean isElementDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
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

    private void click(By locator) {
        waitClickable(locator, 5).click();
    }
}