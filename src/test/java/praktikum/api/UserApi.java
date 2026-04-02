package praktikum.api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApi {
    private static final String REGISTER_PATH = "/api/auth/register";
    private static final String LOGIN_PATH = "/api/auth/login";
    private static final String USER_PATH = "/api/auth/user";

    public Response createUser(UserCredentials user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(REGISTER_PATH);
    }

    public Response login(UserCredentials user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(LOGIN_PATH);
    }

    public Response deleteUser(String token) {
        return given()
                .header("Authorization", token)
                .when()
                .delete(USER_PATH);
    }
}