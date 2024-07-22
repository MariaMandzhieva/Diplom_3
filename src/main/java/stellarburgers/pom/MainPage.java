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
    // заголовок Соберите бургер
    private final By assembleBurger = By.xpath(".//h1[text()='Соберите бургер']");
    // кнопка Оформить заказ
    private final By createOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    // кнопка Булки в невыбранном состоянии
    private final By bunsButton = By.xpath(".//span[text() = 'Булки']/parent::div");
    // кнопка в конструкторе в выбранном состоянии
     private final By selectedButton = By.xpath(".//div[contains(@class, 'current')]");
    // кнопка Соусы в невыбранном состоянии
    private final By saucesButton = By.xpath(".//span[text()='Соусы']/parent::div");
    // кнопка Начинки в невыбранном состоянии
    private final By fillingsButton = By.xpath(".//span[text()='Начинки']/parent::div");

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

    @Step("Клик по кнопке «Соусы»")
    public void clickSaucesButton() {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.elementToBeClickable(saucesButton));
        driver.findElement(saucesButton).click();
    }

    @Step("Клик по кнопке «Начинки»")
    public void clickFillingsButton() {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.elementToBeClickable(fillingsButton));
        driver.findElement(fillingsButton).click();
    }

    @Step("Получить текст выбранной кнопки")
    public String getSelectedButtonText() {
        return driver.findElement(selectedButton).getText();
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
