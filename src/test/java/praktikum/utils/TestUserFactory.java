package praktikum.utils;

import com.github.javafaker.Faker;
import praktikum.api.UserCredentials;

public class TestUserFactory {
    private static final Faker faker = new Faker();

    public static UserCredentials createRandomUser() {
        return new UserCredentials(
                faker.internet().emailAddress(),
                faker.internet().password(6, 10),
                faker.name().firstName()
        );
    }
}