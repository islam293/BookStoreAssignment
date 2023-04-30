package bookstore.pojoModels;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;

public class Credentials {

    String userName;
    String password;

    public Credentials() {

    }
    public Credentials(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
