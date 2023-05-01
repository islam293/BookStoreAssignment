package QAdemoUIModule.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/*This class contains all the methods and steps performed in the Login Page.*/

public class LoginPage extends PageBase{

    //create constructor for the webDriver
    public LoginPage(WebDriver driver) {super(driver);}

    //Declare all elements used in the page
    By loginButton = By.id("login");
    By userNameTxtBox = By.id("userName");
    By passwordTxtBox = By.id("password");
    By userNameHeader = By.id("userName-value");

    /*Enter the username and the password, log to the app method
    *  and verify the login using the username appeared in the page header*/
    public String loginMethod(String userName, String password){
        sendText(userNameTxtBox,userName);
        sendText(passwordTxtBox,password);
        clickButton(loginButton);
        return getTxt(userNameHeader);
    }

}
