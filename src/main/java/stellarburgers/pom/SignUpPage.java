package stellarburgers.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import stellarburgers.model.User;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class SignUpPage {
    // заголовок Регистрация
    private final By signUpHeader = By.xpath(".//h2[text()='Регистрация']");
    // поле ввода Имя
    private final By nameField = By.xpath("(//input[@class='text input__textfield text_type_main-default'])[1]");
    // поле ввода Email
    private final By emailField = By.xpath("(//input[@class='text input__textfield text_type_main-default'])[2]");
    // поле ввода Пароль
    private final By passwordField = By.xpath("(//input[@class='text input__textfield text_type_main-default'])[3]");
    // кнопка Зарегистрироваться
    private final By signUpButton = By.xpath(".//button[text()='Зарегистрироваться']");
    // кнопка Войти
    private final By loginButton = By.xpath(".//a[text()='Войти']");
    // сообщение Некорректный пароль
    private final By incorrectPassMessage = By.xpath(".//p[text()='Некорректный пароль']");

    private final WebDriver driver;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForSignUpHeader() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(signUpHeader));
    }

    public By getLoginButton() {
        return loginButton;
    }

    @Step("Регистрация пользователя")
    public void signUpUser(User user) {
        waitForSignUpHeader();
        driver.findElement(nameField).sendKeys(user.getName());
        driver.findElement(emailField).sendKeys(user.getEmail());
        driver.findElement(passwordField).sendKeys(user.getPassword());
        driver.findElement(signUpButton).click();
    }

    @Step("Регистрация пользователя с некорректным паролем")
    public void signUpUserWithInccorectPass(User user) {
        waitForSignUpHeader();
        driver.findElement(nameField).sendKeys(user.getName());
        driver.findElement(emailField).sendKeys(user.getEmail());
        driver.findElement(passwordField).sendKeys("12345");
        driver.findElement(signUpButton).click();
        driver.findElement(incorrectPassMessage).isDisplayed();
    }

    @Step("Клик по кнопке «Войти в аккаунт» в форме регистрации")
    public void clickLoginButtonSignUpPage() {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    }

    @Step("Получить текст сообщения об ошибке")
    public String getIncorrectPassMessageText() {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.elementToBeClickable(incorrectPassMessage));
        return driver.findElement(incorrectPassMessage).getText();
    }
}
