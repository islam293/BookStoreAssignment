package tests;

import bookstore.Builders.BookStoreURI;
import bookstore.Builders.SpecBuilders;
import bookstore.pojoModels.Credentials;
import bookstore.pojoModels.ISBNList;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestBase {

    public Response getBookList(){

        Response response =  RestAssured.given(SpecBuilders.getRequestSpec()).
                when().get(BookStoreURI.booksPath).
                then().spec(SpecBuilders.getResponseSpec()).and().extract().response();

        return response;
    }

    public Response createUser(Credentials credentials){

        Response response =  RestAssured.given(SpecBuilders.getRequestSpec()).
                body(credentials).
                when().post(BookStoreURI.userPath).
                then().spec(SpecBuilders.getResponseSpec()).and().extract().response();

        return response;
    }

    public Response generateUserToken(Credentials credentials){
        Response response =  RestAssured.given(SpecBuilders.getRequestSpec()).
                body(credentials).
                when().post(BookStoreURI.tokenPath).
                then().spec(SpecBuilders.getResponseSpec()).and().extract().response();

        return response;
    }
    public Response checkAuthUser(Credentials credentials){
        Response response =  RestAssured.given(SpecBuilders.getRequestSpec()).
                body(credentials).
                when().post(BookStoreURI.authPath).
                then().spec(SpecBuilders.getResponseSpec()).and().extract().response();

        return response;
    }

    public Response addBooksToUserList(ISBNList isbnList,String bearerToken){
        Response response =  RestAssured.given(SpecBuilders.getRequestSpec())
                .header("Authorization",
                        "Bearer " + bearerToken).
                body(isbnList).
                when().post(BookStoreURI.booksPath).
                then().spec(SpecBuilders.getResponseSpec()).and().extract().response();

        return response;
    }

}
