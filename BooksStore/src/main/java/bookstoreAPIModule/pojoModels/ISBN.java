package bookstoreAPIModule.pojoModels;

import lombok.Getter;
import lombok.Setter;

/*This class is to Create Pojo object for ISBN Number.
 * It uses Lombok to create Setters & Getters.*/
@Getter @Setter
public class ISBN {
    String isbn;
    public ISBN() {}
    public ISBN(String isbn) {
        this.isbn = isbn;
    }
}
