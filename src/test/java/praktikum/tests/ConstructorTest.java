package praktikum.tests;

import io.qameta.allure.Description;
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
    @Description("Проверяет, что после клика на вкладку 'Булки' (с предварительным кликом на 'Соусы') активным становится раздел с булками")
    public void shouldSwitchToBunsSection() {
        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();

        assertTrue("Вкладка 'Булки' не активировалась",
                mainPage.isBunsTabActive());
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    @Description("Проверяет, что после клика на вкладку 'Соусы' активным становится раздел с соусами")
    public void shouldSwitchToSaucesSection() {
        mainPage.clickSaucesTab();

        assertTrue("Вкладка 'Соусы' не активировалась",
                mainPage.isSaucesTabActive());
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    @Description("Проверяет, что после клика на вкладку 'Начинки' активным становится раздел с начинками")
    public void shouldSwitchToFillingsSection() {
        mainPage.clickFillingsTab();

        assertTrue("Вкладка 'Начинки' не активировалась",
                mainPage.isFillingsTabActive());
    }
}