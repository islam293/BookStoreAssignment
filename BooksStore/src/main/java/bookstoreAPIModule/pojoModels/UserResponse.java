package bookstoreAPIModule.pojoModels;

import lombok.Getter;
import java.util.List;

/*This class is to Create Pojo object for user Response Components .
 * It uses Lombok to create Getters.*/
@Getter
public class UserResponse {
    String userID;
    String username;
    List<BooksList> books;
}
