package bookstoreAPIModule.pojoModels;

import lombok.Getter;

/*This class is to Create Pojo object for Token Response Components .
 * It uses Lombok to create Getters.*/
@Getter
public class TokenResponse {
    String token;
    String expires;
    String status;
    String result;
}
