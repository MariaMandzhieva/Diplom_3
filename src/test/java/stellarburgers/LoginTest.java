package stellarburgers;


import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stellarburgers.api.UserApi;
import stellarburgers.model.User;
import stellarburgers.pom.LoginPage;
import stellarburgers.pom.MainPage;
import stellarburgers.pom.ResetPasswordPage;
import stellarburgers.pom.SignUpPage;

import java.time.Duration;

import static driver.WebDriverCreator.createWebDriver;
import static org.junit.Assert.assertEquals;

public class LoginTest {
    private WebDriver driver;
    private User user;
    private UserApi userApi;


    @Before
    public void setUp() {
        driver = createWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        userApi = new UserApi();

        String email = (RandomStringUtils.randomAlphabetic(10) + "@yandex.ru").toLowerCase();
        String password = RandomStringUtils.randomAlphabetic(8);
        String name = RandomStringUtils.randomAlphabetic(8);

        user = new User(email, password, name);
        userApi.createUser(user);
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void loginButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);

        String actualTextOrderButton = mainPage.getCreateOrderButtonText();
        String expectedTextOrderButton = "Оформить заказ";
        assertEquals(expectedTextOrderButton, actualTextOrderButton);
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void personalAccButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickPersonalAccButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);

        String actualTextOrderButton = mainPage.getCreateOrderButtonText();
        String expectedTextOrderButton = "Оформить заказ";
        assertEquals(expectedTextOrderButton, actualTextOrderButton);
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginButtonSignUpPageTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);

        WebElement elementSignUP = driver.findElement(loginPage.getSignUpButton());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementSignUP);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(loginPage.getSignUpButton()));

        loginPage.clickSignUpButton();

        SignUpPage signUpPage = new SignUpPage(driver);

        WebElement elementLogin = driver.findElement(signUpPage.getLoginButton());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementLogin);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(signUpPage.getLoginButton()));

        signUpPage.clickLoginButtonSignUpPage();

        loginPage.login(user);

        String actualTextOrderButton = mainPage.getCreateOrderButtonText();
        String expectedTextOrderButton = "Оформить заказ";
        assertEquals(expectedTextOrderButton, actualTextOrderButton);
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginButtonResetPasswordPageTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);

        WebElement elementResetPass = driver.findElement(loginPage.getResetPassButton());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementResetPass);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(loginPage.getResetPassButton()));

        loginPage.clickResetPassButton();

        ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);

        WebElement elementLogin = driver.findElement(resetPasswordPage.getLoginButton());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementLogin);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(resetPasswordPage.getLoginButton()));

        resetPasswordPage.clickLoginButtonResetPasswordPage();

        loginPage.login(user);

        String actualTextOrderButton = mainPage.getCreateOrderButtonText();
        String expectedTextOrderButton = "Оформить заказ";
        assertEquals(expectedTextOrderButton, actualTextOrderButton);
    }

    @After
    @Step("Удаление пользователя и закрытие браузера")
    public void cleanUp() {
        String token = userApi.loginUser(user)
                .extract().body().path("accessToken");
        user.setAccessToken(token);

        if (user.getAccessToken() != null) {
            userApi.deleteUser(user);
        }
        driver.quit();
    }
}

