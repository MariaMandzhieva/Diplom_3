package stellarburgers.api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import stellarburgers.model.User;

import static io.restassured.RestAssured.given;

public class UserApi extends BaseURI {
    private static final String USER_URI = BASE_URI + "auth/";

    @Step("Создание пользователя")
    public ValidatableResponse createUser(User user) {
        return RestAssured.given()
                .spec(getReqSpec())
                .body(user)
                .post(USER_URI + "register")
                .then();
    }

    @Step("Авторизация пользователя")
    public ValidatableResponse loginUser(User user) {
        return RestAssured.given()
                .spec(getReqSpec())
                .body(user)
                .post(USER_URI + "login")
                .then();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(User user) {
        return given()
                .spec(getReqSpec())
                .header("accessToken", user.getAccessToken())
                .when()
                .delete(USER_URI + "user")
                .then();
    }
}
