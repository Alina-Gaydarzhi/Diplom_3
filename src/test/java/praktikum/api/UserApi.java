package praktikum.api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApi {
    private static final String BASE_URL = "https://stellarburgers.education-services.ru";
    private static final String REGISTER_PATH = "/api/auth/register";
    private static final String LOGIN_PATH = "/api/auth/login";
    private static final String USER_PATH = "/api/auth/user";

    public String register(UserCredentials user) {
        Response response = given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(REGISTER_PATH);
        return response.path("accessToken");
    }

    public String login(UserCredentials user) {
        Response response = given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(LOGIN_PATH);
        return response.path("accessToken");
    }

    public void delete(String token) {
        given()
                .baseUri(BASE_URL)
                .header("Authorization", token)
                .when()
                .delete(USER_PATH);
    }
}