package bookstore.pojoModels;

import java.util.List;

public class ISBNList {

    String userId;
    List<ISBN> collectionOfIsbns;

    public ISBNList() {
    }

    public ISBNList(String userId, List<ISBN> collectionOfIsbns) {
        this.userId = userId;
        this.collectionOfIsbns = collectionOfIsbns;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<ISBN> getCollectionOfIsbns() {
        return collectionOfIsbns;
    }

    public void setCollectionOfIsbns(List<ISBN> collectionOfIsbns) {
        this.collectionOfIsbns = collectionOfIsbns;
    }

}
