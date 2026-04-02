package praktikum.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import praktikum.base.BaseTest;
import praktikum.pages.MainPage;

import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest {

    private MainPage mainPage;

    @Before
    public void setUpConstructor() {
        mainPage = new MainPage(driver);
        mainPage.open();
    }

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    public void shouldSwitchToBunsSection() {
        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();

        assertTrue("Вкладка 'Булки' не активировалась",
                mainPage.isBunsTabActive());
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    public void shouldSwitchToSaucesSection() {
        mainPage.clickSaucesTab();

        assertTrue("Вкладка 'Соусы' не активировалась",
                mainPage.isSaucesTabActive());
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    public void shouldSwitchToFillingsSection() {
        mainPage.clickFillingsTab();

        assertTrue("Вкладка 'Начинки' не активировалась",
                mainPage.isFillingsTabActive());
    }
}