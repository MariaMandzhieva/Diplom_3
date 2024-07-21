package stellarburgers.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stellarburgers.model.User;

public class LoginPage {
    // заголовок Вход
    private final By headerEntrance = By.xpath(".//h2[text()='Вход']");
    // поле ввода почты
    private final By email = By.xpath(".//input[@class='text input__textfield text_type_main-default' and @type='text']");
    // поле ввода пароля
    private final By password = By.xpath(".//input[@class='text input__textfield text_type_main-default' and @type='password']");
    // кнопка Войти
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    // кнопка Зарегистрироваться
    private final By signUpButton = By.xpath(".//a[text()='Зарегистрироваться']");
    // кнопка Восстановить пароль
    private final By resetPassButton = By.xpath(".//a[text()='Восстановить пароль']");

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private void waitForPageLoad() {
        new WebDriverWait(driver, 10).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public By getSignUpButton() {
        return signUpButton;
    }

    public By getResetPassButton() {
        return resetPassButton;
    }

    public By getLoginButton() {
        return loginButton;
    }

    @Step("Авторизация пользователя")
    public void login(User user) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(email).sendKeys(user.getEmail());
        driver.findElement(password).sendKeys(user.getPassword());
        driver.findElement(loginButton).click();
    }

    @Step("Клик по кнопке Зарегистрироваться")
    public void clickSignUpButton() {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.elementToBeClickable(signUpButton));
        driver.findElement(signUpButton).click();
    }

    @Step("Клик по кнопке Восстановить пароль")
    public void clickResetPassButton() {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.elementToBeClickable(resetPassButton));
        driver.findElement(resetPassButton).click();
    }

    @Step("Проверка наличия заголовка Вход")
    public boolean isHeaderEntranceDisplayed() {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.elementToBeClickable(headerEntrance));
        return driver.findElement(headerEntrance).isDisplayed();
    }
}
