package tests;

import models.CredentialsModel;
import models.IsbnModel;

public class TestData {

    private static final String login = "test5",
            password = "Test567!";

    public static CredentialsModel credentials = new CredentialsModel(login, password);

    private static final String isbn = "9781449325862";
    public static IsbnModel book = new IsbnModel(isbn);
}
