package tests;

import models.AddBooksListModel;
import api.AuthorizationApi;
import api.BooksApi;
import models.DeleteBookResponseModel;
import models.IsbnModel;
import models.LoginResponseModel;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

import java.util.ArrayList;
import java.util.List;

import static tests.TestData.book;
import static tests.TestData.credentials;

public class ProfileBooksListTests extends TestBase {

    String bookName = "Git Pocket Guide";

    AuthorizationApi authorizationApi = new AuthorizationApi();
    BooksApi booksApi = new BooksApi();
    ProfilePage profile = new ProfilePage();

    @Test
    void addBookToProfileTest() {
        LoginResponseModel loginResponse = authorizationApi.login(credentials);
        booksApi.deleteAllBooks(loginResponse);

        IsbnModel isbnModel = new IsbnModel(book.getIsbn());
        List<IsbnModel> isbnList = new ArrayList<>();
        isbnList.add(isbnModel);

        AddBooksListModel booksList = new AddBooksListModel();
        booksList.setUserId(loginResponse.getUserId());
        booksList.setCollectionOfIsbns(isbnList);

        booksApi.addBook(loginResponse, booksList);

        profile
                .openProfileWithCookies(loginResponse)
                .checkBookAddedToProfile(bookName);
    }

    @Test
    void deleteBookFromProfileTest() {
        LoginResponseModel loginResponse = authorizationApi.login(credentials);
        booksApi.deleteAllBooks(loginResponse);

        IsbnModel isbnModel = new IsbnModel(book.getIsbn());
        List<IsbnModel> isbnList = new ArrayList<>();
        isbnList.add(isbnModel);

        AddBooksListModel booksList = new AddBooksListModel();
        booksList.setUserId(loginResponse.getUserId());
        booksList.setCollectionOfIsbns(isbnList);

        booksApi.addBook(loginResponse, booksList);

        DeleteBookResponseModel deleteBook = new DeleteBookResponseModel();
        deleteBook.setIsbn(book.getIsbn());
        deleteBook.setUserId(loginResponse.getUserId());
        booksApi.deleteBook(loginResponse, deleteBook);

        profile
                .openProfileWithCookies(loginResponse)
                .checkBookDeletedFromProfile(bookName);
    }
}
