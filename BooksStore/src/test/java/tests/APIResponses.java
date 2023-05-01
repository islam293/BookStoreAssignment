package tests;

import bookstoreAPIModule.Builders.BookStoreURI;
import bookstoreAPIModule.Builders.SpecBuilders;
import bookstoreAPIModule.pojoModels.Credentials;
import bookstoreAPIModule.pojoModels.ISBNList;
import io.restassured.RestAssured;
import io.restassured.response.Response;


/*This class generate all the responses for the requests that will be used in test*/
public class APIResponses {
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

    public Response addBooksToUserList(ISBNList isbnList, String bearerToken){
        Response response =  RestAssured.given(SpecBuilders.getRequestSpec()).
                header("Authorization",
                        "Bearer " + bearerToken).
                body(isbnList).
                when().post(BookStoreURI.booksPath).
                then().spec(SpecBuilders.getResponseSpec()).and().extract().response();

        return response;
    }

    public Response getBooksInUserList(String id, String bearerToken){
        Response response =  RestAssured.given(SpecBuilders.getRequestSpec()).
                pathParam("UUID",id).
                header("Authorization",
                        "Bearer " + bearerToken).
                when().get(BookStoreURI.userPath+BookStoreURI.userId).
                then().spec(SpecBuilders.getResponseSpec()).and().extract().response();

        return response;
    }

}
