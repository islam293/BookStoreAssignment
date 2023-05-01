package bookstoreAPIModule.pojoModels;

import lombok.Getter;
import lombok.Setter;

/*This class is to Create Pojo object for Login Credentials.
 * It uses Lombok to create Setters & Getters.*/
@Getter @Setter
public class Credentials {
    String userName;
    String password;
    public Credentials() {}
    public Credentials(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
