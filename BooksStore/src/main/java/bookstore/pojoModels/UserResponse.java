package bookstore.pojoModels;

import java.util.List;

public class UserResponse {

    String userID;
    String username;
    List<BooksList> books;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<BooksList> getBooks() {
        return books;
    }

    public void setBooks(List<BooksList> books) {
        this.books = books;
    }


}
