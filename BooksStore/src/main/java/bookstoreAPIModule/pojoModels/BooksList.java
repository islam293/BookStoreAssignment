package bookstoreAPIModule.pojoModels;

import lombok.Getter;
import java.util.List;

/*This class is to Create Pojo object for Books Lists.
 * It uses Lombok to create Getters.*/
@Getter
public class BooksList {
    List<Book> books;
}
