package tests;

import QAdemoUIModule.Pages.BookStoreAppPage;
import QAdemoUIModule.Pages.LandingPage;
import QAdemoUIModule.Pages.LoginPage;
import QAdemoUIModule.Pages.ProfilePage;
import bookstoreAPIModule.pojoModels.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.DataRandomGenerator;

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertTrue;


public class BookstoreQADemoTest extends TestBase {

    @Test
    public void apiUITest() {

        //API Steps
        APIResponses apiResponses = new APIResponses();
        Response response;
        //Generate UserName & Password
        Credentials crd = new Credentials();
        crd.setUserName(DataRandomGenerator.generateRandomString(6));
        crd.setPassword(DataRandomGenerator.generateRandomString(10));
        //Create User
        response = apiResponses.createUser(crd);
        assertThat(response.getStatusCode(),equalTo(201));
        UserResponse userResponse = response.as(UserResponse.class);
        //Generate Token
        response = apiResponses.generateUserToken(crd);
        assertThat(response.getStatusCode(),equalTo(200));
        TokenResponse tokenResponse = response.as(TokenResponse.class);
        //Check User Authorization
        response = apiResponses.checkAuthUser(crd);
        assertThat(response.getStatusCode(),equalTo(200));
        assertTrue(response.as(Boolean.class));
        //Get All BookList
        response = apiResponses.getBookList();
        assertThat(response.getStatusCode(),equalTo(200));
        BooksList booksList = response.as(BooksList.class);
        //Filter BookList with Publisher "1st book publisher"
        String publisher = booksList.getBooks().get(0).getPublisher();
        List<Book> filterdList = booksList.getBooks().stream().filter(e -> e.getPublisher().equals(publisher)).
                collect(Collectors.toList());
        //Get All ISBN numbers for all books with the same publisher
        ISBNList isbnList = new ISBNList();
        isbnList.setUserId(userResponse.getUserID());
        List<ISBN> list = new ArrayList<>();
        for(int i =0; i<filterdList.size(); i++){
            String isbnNumber = filterdList.get(i).getIsbn();
            ISBN isbn = new ISBN();
            isbn.setIsbn(isbnNumber);
            list.add(isbn);
        }
        isbnList.setCollectionOfIsbns(list);
        //Assign Books to user
        response = apiResponses.addBooksToUserList(isbnList,tokenResponse.getToken());
        assertThat(response.getStatusCode(),equalTo(201));
        //Get The book list assigned on user
        response = apiResponses.getBooksInUserList(userResponse.getUserID(),tokenResponse.getToken());
        UserBookListResponse userBookListResponse = response.as(UserBookListResponse.class);
        assertThat(response.getStatusCode(),equalTo(200));
        List<Book> userBookListAPI = userBookListResponse.getBooks();
        //Sort The List
        Collections.sort(userBookListAPI);

        //UI Steps
        startDriver();
        navigateToURL();
        LandingPage landingPage = new LandingPage(driver);
        assertTrue(landingPage.OpenAppPage().contains("Book Store"));

        BookStoreAppPage bookStoreAppPage = new BookStoreAppPage(driver);
        assertTrue(bookStoreAppPage.OpenLoginPage().contains("Login"));
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.loginMethod(crd.getUserName(), crd.getPassword()).contains(crd.getUserName()));

        bookStoreAppPage.openProfileBookList();
        ProfilePage profilePage = new ProfilePage(driver);

        List<Book>userBookListUI = profilePage.getBookDetails();
        Collections.sort(userBookListUI);

        //Validate on the ook numbers from both APIs & UI
        assertTrue(userBookListAPI.size()==userBookListUI.size());
        //Validate on the value of each book from both APIs & UI
        for(int i=0; i<userBookListAPI.size();i++){
            assertTrue(userBookListAPI.get(i).getIsbn().equals(userBookListUI.get(i).getIsbn()));
            assertTrue(userBookListAPI.get(i).getAuthor().equals(userBookListUI.get(i).getAuthor()));
            assertTrue(userBookListAPI.get(i).getDescription().equals(userBookListUI.get(i).getDescription()));
            assertTrue(userBookListAPI.get(i).getTitle().equals(userBookListUI.get(i).getTitle()));
            assertTrue(userBookListAPI.get(i).getWebsite().equals(userBookListUI.get(i).getWebsite()));
            assertTrue(userBookListAPI.get(i).getSubTitle().equals(userBookListUI.get(i).getSubTitle()));
            assertTrue(userBookListAPI.get(i).getPages()==userBookListUI.get(i).getPages());
            assertTrue(userBookListAPI.get(i).getPublisher().equals(userBookListUI.get(i).getPublisher()));
        }
        closeBrowser();
    }
}
