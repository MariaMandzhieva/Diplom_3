package stellarburgers;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import stellarburgers.pom.MainPage;

import java.time.Duration;

import static driver.WebDriverCreator.*;
import static org.junit.Assert.assertEquals;


public class ConstructorTest {
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        driver = createWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        mainPage = new MainPage(driver);
        mainPage.openMainPage();
    }

    @Test
    @DisplayName("Проверка перехода к разделу «Булки»")
    public void bunsSectionTest() {
        mainPage.clickSaucesButton();
        mainPage.clickBunsButton();
        String expectedText = "Булки";
        String actualText = mainPage.getSelectedButtonText();
        assertEquals(expectedText, actualText);
    }

    @Test
    @DisplayName("Проверка перехода к разделу «Соусы»")
    public void saucesSectionTest() {
        mainPage.clickSaucesButton();
        String expectedText = "Соусы";
        String actualText = mainPage.getSelectedButtonText();
        assertEquals(expectedText, actualText);
    }

    @Test
    @DisplayName("Проверка перехода к разделу «Начинки»")
    public void fillingsSectionTest() {
        mainPage.clickFillingsButton();
        String expectedText = "Начинки";
        String actualText = mainPage.getSelectedButtonText();
        assertEquals(expectedText, actualText);
    }

    @After
    @Step("Закрытие браузера")
    public void cleanUp() {
        driver.quit();
    }
}
