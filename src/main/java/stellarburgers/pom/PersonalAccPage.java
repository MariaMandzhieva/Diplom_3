package stellarburgers.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalAccPage {
    // кнопка Выход
    private final By exitButton = By.xpath(".//button[text()='Выход']");
    // логотип
    private final By logo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");
    // кнопка Конструктор
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    // кнопка Профиль
    private final By headerProfile = By.xpath(".//p[text()='Конструктор']");

    private final WebDriver driver;

    public PersonalAccPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по кнопке Выход")
    public void clickExitButton() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(headerProfile));
        driver.findElement(exitButton).click();
    }

    @Step("Клик по кнопке Конструктор")
    public void clickConstructorButton() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(headerProfile));
        driver.findElement(constructorButton).click();
    }

    @Step("Клик по Логотипу")
    public void clickLogo() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(headerProfile));
        driver.findElement(logo).click();
    }

    @Step("Проверка наличия раздела Профиль")
    public boolean isHeaderProfileDisplayed() {
        return driver.findElement(headerProfile).isDisplayed();
    }
}
