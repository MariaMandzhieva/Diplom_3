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
import stellarburgers.pom.SignUpPage;

import java.time.Duration;

import static driver.WebDriverCreator.createWebDriver;
import static org.junit.Assert.assertEquals;

public class SignUpTest {
    private WebDriver driver;
    private User user;
    private UserApi userApi;
    private MainPage mainPage;

    @Before
    public void setUp() {
        driver = createWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        String email = (RandomStringUtils.randomAlphabetic(10) + "@yandex.ru").toLowerCase();
        String password = RandomStringUtils.randomAlphabetic(8);
        String name = RandomStringUtils.randomAlphabetic(8);

        user = new User(email, password, name);
        userApi = new UserApi();

        mainPage = new MainPage(driver);
        mainPage.openMainPage();
    }

    @Test
    @DisplayName("Проверка успешной регистрации")
    public void signUpTest() {
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);

        WebElement elementSignUP = driver.findElement(loginPage.getSignUpButton());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementSignUP);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(loginPage.getSignUpButton()));

        loginPage.clickSignUpButton();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.signUpUser(user);

        loginPage.login(user);

        String actualTextOrderButton = mainPage.getCreateOrderButtonText();
        String expectedTextOrderButton = "Оформить заказ";
        assertEquals(expectedTextOrderButton, actualTextOrderButton);
    }

    @Test
    @DisplayName("Проверка появления сообщения Некорретный пароль")
    public void signUpWithIncorrectPassTest() {
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);

        WebElement elementSignUP = driver.findElement(loginPage.getSignUpButton());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementSignUP);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(loginPage.getSignUpButton()));

        loginPage.clickSignUpButton();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.signUpUserWithInccorectPass(user);

        String actualText = signUpPage.getIncorrectPassMessageText();
        String expectedText = "Некорректный пароль";
        assertEquals(expectedText, actualText);
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
