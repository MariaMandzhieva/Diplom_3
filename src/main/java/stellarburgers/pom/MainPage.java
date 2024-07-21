package stellarburgers.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class MainPage {
    // ссылка на главкую страницу сервиса
    public static final String URL = "https://stellarburgers.nomoreparties.site/";
    // кнопка Войти в аккаунт
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    // кнопка Личный кабинет
    private final By personalAccButton = By.xpath(".//p[text()='Личный Кабинет']");
    // кнопка Конструктор
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    // заголовок Соберите бургер
    private final By assembleBurger = By.xpath(".//h1[text()='Соберите бургер']");
    // Логотип
    private final By logo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");
    // кнопка Оформить заказ
    private final By createOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    // кнопка Булки
    private final By bunsButton = By.xpath(".//span[text()='Булки']");
    // Заголовок раздела Булки
    private final By headerBunsSection = By.xpath(".//h2[text()='Булки']");
    // кнопка Соусы
    private final By saucesButton = By.xpath(".//span[text()='Соусы']");
    // Заголовок раздела Соусы
    private final By headerSaucesSection = By.xpath(".//h2[text()='Соусы']");
    // кнопка Начинки
    private final By fillingsButton = By.xpath(".//span[text()='Начинки']");
    // Заголовок раздела Начинки
    private final By headerFillingsSection = By.xpath(".//h2[text()='Начинки']");

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открыть главную страницу")
    public void openMainPage() {
        driver.get(URL);
    }

    @Step("Клик по кнопке «Войти в аккаунт» на главной")
    public void clickLoginButton() {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    }

    @Step("Клик по кнопке «Личный кабинет» на главной")
    public void clickPersonalAccButton() {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.elementToBeClickable(personalAccButton));
        driver.findElement(personalAccButton).click();
    }

    @Step("Клик по кнопке «Булки»")
    public void clickBunsButton() {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.elementToBeClickable(bunsButton));
        driver.findElement(bunsButton).click();
    }

    @Step("Проверка того, что появился заголовок раздела «Булки»")
    public boolean isHeaderBunsSectionDisplayed() {
        return driver.findElement(headerBunsSection).isDisplayed();
    }

    @Step("Клик по кнопке «Соусы»")
    public void clickSaucesButton() {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.elementToBeClickable(saucesButton));
        driver.findElement(saucesButton).click();
    }

    @Step("Проверка того, что появился заголовок раздела «Соусы»")
    public boolean isHeaderSaucesSectionDisplayed() {
        return driver.findElement(headerSaucesSection).isDisplayed();
    }

    @Step("Клик по кнопке «Начинки»")
    public void clickFillingsButton() {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.elementToBeClickable(fillingsButton));
        driver.findElement(fillingsButton).click();
    }

    @Step("Проверка того, что появился заголовок раздела «Начинки»")
    public boolean isHeaderFillingsSectionDisplayed() {
        return driver.findElement(headerFillingsSection).isDisplayed();
    }

    @Step("Проверка наличия заголовка Соберите бургер")
    public boolean isHeaderAssembleBurgerDisplayed() {
        return driver.findElement(assembleBurger).isDisplayed();
    }

    @Step("Получить текст кнопки Оформить заказ")
    public String getCreateOrderButtonText() {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.elementToBeClickable(createOrderButton));
        return driver.findElement(createOrderButton).getText();
    }
}
