package QAdemoUIModule.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/*This class contains all the methods and steps performed in the BookStoreApp Page.*/
public class BookStoreAppPage extends PageBase{

    //create constructor for the webDriver
    public BookStoreAppPage(WebDriver driver) {super(driver);}

    //Declare all elements used in the page
    By loginButton = By.id("login");
    By header = By.xpath("//div[@class='pattern-backgound playgound-header']/div[@class='main-header']");
    By profile = By.xpath("//div[@class='element-group']/div[@class='element-list collapse show']/ul[@class='menu-list']/li[@id='item-3']");

    //Click on the login button and validate that it's opened using the header
    public String OpenLoginPage(){
        waitMethod();
        clickButtonJS(loginButton);
        String txt = getTxt(header);
        return txt;
    }

    //Click on the Profile List and validate that it's opened using the header
    public String openProfileBookList(){
        clickButtonJS(profile);
        return getTxt(header);
    }


}
