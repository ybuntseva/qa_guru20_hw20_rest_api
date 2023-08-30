package tests;

import api.AuthorizationApi;
import models.LoginResponseModel;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static tests.TestData.credentials;
public class LoginTest extends TestBase {
    AuthorizationApi authorizationApi = new AuthorizationApi();
    @Test
    void successfulLoginTest() {
        LoginResponseModel loginResponse = authorizationApi.login(credentials);

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", loginResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", loginResponse.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginResponse.getExpires()));

        open("/profile");
        $("#userName-value").shouldHave(text(credentials.getUserName()));
    }
}
