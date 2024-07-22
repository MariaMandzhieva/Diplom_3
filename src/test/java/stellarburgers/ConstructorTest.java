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
        mainPage.isHeaderBunsSectionDisplayed();
    }

    @Test
    @DisplayName("Проверка перехода к разделу «Соусы»")
    public void saucesSectionTest() {
        mainPage.clickSaucesButton();
        mainPage.isHeaderSaucesSectionDisplayed();
    }

    @Test
    @DisplayName("Проверка перехода к разделу «Начинки»")
    public void fillingsSectionTest() {
        mainPage.clickFillingsButton();
        mainPage.isHeaderFillingsSectionDisplayed();
    }

    @After
    @Step("Закрытие браузера")
    public void cleanUp() {
        driver.quit();
    }
}
