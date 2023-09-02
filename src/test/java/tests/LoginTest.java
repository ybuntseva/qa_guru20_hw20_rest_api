package tests;

import api.AuthorizationApi;
import models.LoginResponseModel;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

import static tests.TestData.credentials;

public class LoginTest extends TestBase {

    AuthorizationApi authorizationApi = new AuthorizationApi();
    ProfilePage profile = new ProfilePage();

    @Test
    void successfulLoginTest() {
        LoginResponseModel loginResponse = authorizationApi.login(credentials);

        profile
                .openProfileWithCookies(loginResponse)
                .checkUserName(credentials.getUserName());
    }
}
