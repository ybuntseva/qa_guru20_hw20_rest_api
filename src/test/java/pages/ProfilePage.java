package pages;

import com.codeborne.selenide.SelenideElement;
import models.LoginResponseModel;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ProfilePage {

    SelenideElement
            userName = $("#userName-value"),
            bookList = $(".rt-tbody");

    public ProfilePage openProfileWithCookies(LoginResponseModel loginResponse) {
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", loginResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", loginResponse.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginResponse.getExpires()));

        return this;
    }

    public ProfilePage checkUserName(String username) {
        open("/profile");
        userName.shouldHave(text(username));

        return this;
    }

    public ProfilePage checkBookAddedToProfile(String bookName) {
        open("/profile");
        bookList.shouldHave(text(bookName));

        return this;
    }

    public ProfilePage checkBookDeletedFromProfile(String bookName) {
        open("/profile");
        bookList.shouldNot(text(bookName));

        return this;
    }
}
