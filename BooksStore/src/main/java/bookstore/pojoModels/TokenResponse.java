package bookstore.pojoModels;

public class TokenResponse {

    String token;
    String expires;
    String status;
    String result;
    public String getToken() {
        return token;
    }

    public String getExpires() {
        return expires;
    }

    public String getStatus() {
        return status;
    }

    public String getResult() {
        return result;
    }


}
