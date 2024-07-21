package stellarburgers;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import stellarburgers.api.UserApi;
import stellarburgers.model.User;
import stellarburgers.pom.LoginPage;
import stellarburgers.pom.MainPage;
import stellarburgers.pom.PersonalAccPage;

import java.time.Duration;

import static driver.WebDriverCreator.createWebDriver;

public class PersonalAccTest {
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
    @DisplayName("Проверка перехода в Личный кабинет по клику на «Личный кабинет»")
    public void openPersonalAccByPersonalAccButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);

        mainPage.clickPersonalAccButton();

        PersonalAccPage personalAccPage = new PersonalAccPage(driver);
        personalAccPage.isHeaderProfileDisplayed();
    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета в конструктор по клику на «Конструктор")
    public void openConstructorByConstructorButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);

        mainPage.clickPersonalAccButton();

        PersonalAccPage personalAccPage = new PersonalAccPage(driver);
        personalAccPage.clickConstructorButton();

        mainPage.isHeaderAssembleBurgerDisplayed();
    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета в конструктор по клику на логотип")
    public void openConstructorByLogoButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);

        mainPage.clickPersonalAccButton();

        PersonalAccPage personalAccPage = new PersonalAccPage(driver);
        personalAccPage.clickLogo();

        mainPage.isHeaderAssembleBurgerDisplayed();
    }

    @Test
    @DisplayName("Проверка выхода по кнопке «Выйти» в личном кабинете")
    public void exitTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);

        mainPage.clickPersonalAccButton();

        PersonalAccPage personalAccPage = new PersonalAccPage(driver);
        personalAccPage.clickExitButton();

        loginPage.isHeaderEntranceDisplayed();
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
