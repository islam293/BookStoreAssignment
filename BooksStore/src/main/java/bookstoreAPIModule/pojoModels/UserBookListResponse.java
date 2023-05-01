package bookstoreAPIModule.pojoModels;

/*This class is to Create Pojo object for user books list Response Components .
 * It uses Lombok to create Getters.*/
import lombok.Getter;
import java.util.List;
@Getter
public class UserBookListResponse {
    String userId;
    String username;
    List<Book> books;

}
