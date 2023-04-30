package tests;

import bookstore.pojoModels.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.DataRandomGenerator;

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertTrue;


public class TestClass extends TestBase {

    @Test
    public void testMethod() {

        Response response;

        Credentials crd = new Credentials();
        crd.setUserName(DataRandomGenerator.generateRandomString(6));
        crd.setPassword(DataRandomGenerator.generateRandomString(10));

        response = createUser(crd);
        assertThat(response.getStatusCode(),equalTo(201));
        UserResponse userResponse = response.as(UserResponse.class);
        response = generateUserToken(crd);
        assertThat(response.getStatusCode(),equalTo(200));
        TokenResponse tokenResponse = response.as(TokenResponse.class);
        response = checkAuthUser(crd);
        assertThat(response.getStatusCode(),equalTo(200));
        assertTrue(response.as(Boolean.class));

        response =getBookList();
        assertThat(response.getStatusCode(),equalTo(200));
        BooksList booksList = response.as(BooksList.class);
        String publisher = booksList.getBooks().get(0).getPublisher();
        List<Book> filterdList = booksList.getBooks().stream().filter(e -> e.getPublisher().equals(publisher)).
                collect(Collectors.toList());

        ISBNList isbnList = new ISBNList();
        isbnList.setUserId(userResponse.getUserID());
        List<ISBN> list = new ArrayList<>();
        ISBN isbn = new ISBN();

        for(int i =0; i<filterdList.size(); i++){
            String isbnNumber = filterdList.get(i).getIsbn();
            isbn.setIsbn(isbnNumber);
            list.add(isbn);
            System.out.println(filterdList.get(i).getIsbn());
        }
        isbnList.setCollectionOfIsbns(list);

        response = addBooksToUserList(isbnList,tokenResponse.getToken());
        assertThat(response.getStatusCode(),equalTo(201));
        BooksList addedBooksList = response.as(BooksList.class);
        //addedBooksList.getBooks();

    }
}
