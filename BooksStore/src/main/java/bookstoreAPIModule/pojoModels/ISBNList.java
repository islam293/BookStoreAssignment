package bookstoreAPIModule.pojoModels;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

/*This class is to Create Pojo object for List of ISBN Number .
 * It uses Lombok to create Setters & Getters.*/
@Getter @Setter
public class ISBNList {
    String userId;
    List<ISBN> collectionOfIsbns;
    public ISBNList() {}
    public ISBNList(String userId, List<ISBN> collectionOfIsbns) {
        this.userId = userId;
        this.collectionOfIsbns = collectionOfIsbns;
    }

}
