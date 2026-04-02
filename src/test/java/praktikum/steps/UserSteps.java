package praktikum.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import praktikum.api.UserApi;
import praktikum.api.UserCredentials;
import praktikum.utils.TestUserFactory;

public class UserSteps {
    private final UserApi userApi = new UserApi();
    private UserCredentials currentUser;
    private String accessToken;

    @Step("Создать случайного пользователя")
    public UserCredentials createRandomUser() {
        currentUser = TestUserFactory.createRandomUser();
        Response response = userApi.createUser(currentUser);
        accessToken = response.then().extract().path("accessToken");
        return currentUser;
    }

    @Step("Удалить текущего пользователя")
    public void deleteUser() {
        if (accessToken != null) {
            userApi.deleteUser(accessToken);
        }
    }

    public UserCredentials getCurrentUser() {
        return currentUser;
    }
}