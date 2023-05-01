package QAdemoUIModule.Pages;

import bookstoreAPIModule.pojoModels.Book;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

/*This class contains all the methods and steps performed in the Profile Page.*/
public class ProfilePage extends PageBase{

    //create constructor for the webDriver
    public ProfilePage(WebDriver driver) {super(driver);}

    //Declare all elements used in the page
    By booksTable = By.className("rt-tbody");
    By nextBtn = By.xpath("//div[@class='-next']/button");
    By booksList = By.xpath("//div[@class='rt-tbody']/div/div/div[@class='rt-td']/div[@class='action-buttons']/span[@class='mr-2']/a");
    By isbn = By.xpath("//div[@id='ISBN-wrapper']/div/label[@id='userName-value']");
    By title = By.xpath("//div[@id='title-wrapper']/div/label[@id='userName-value']");
    By subtitle = By.xpath("//div[@id='subtitle-wrapper']/div/label[@id='userName-value']");
    By author = By.xpath("//div[@id='author-wrapper']/div/label[@id='userName-value']");
    By publisher = By.xpath("//div[@id='publisher-wrapper']/div/label[@id='userName-value']");
    By totalPages = By.xpath("//div[@id='pages-wrapper']/div/label[@id='userName-value']");
    By description = By.xpath("//div[@id='description-wrapper']/div/label[@id='userName-value']");
    By website = By.xpath("//div[@id='website-wrapper']/div/label[@id='userName-value']");
    By backButton = By.xpath("//button[@id='addNewRecordButton'] [normalize-space(.)='Back To Book Store']");
    By backToStore = By.id("gotoStore");
    By profile = By.xpath("//div[@class='element-group']/div[@class='element-list collapse show']/ul[@class='menu-list']/li[@id='item-3']");

    //Get the number of books assigned to the user
    public int getProfileBooksNumber(){
        WebElement books = getWebElement(booksTable);
        List<WebElement> bookRows = books.findElements(By.xpath("*"));
        int booksNumber = 0;

        //Loop to find the empty row
        for(int i = 0; i < bookRows.size(); i++){
            WebElement row = bookRows.get(i);
            WebElement rowPattern = row.findElement(By.xpath("div"));
            String className = rowPattern.getAttribute("class");
            if(className.contains("padRow")){
                break;
            }
            if(i==bookRows.size()-1){
                if(driver.findElement(nextBtn).isEnabled()){
                    clickButtonJS(nextBtn);
                    i=-1;
                    waitMethod();
                    bookRows = books.findElements(By.xpath("*"));
                }
            }
            booksNumber++;
        }
        if(booksNumber > bookRows.size()){
            //return to the profile page with the 1st book list assigned on the user
            clickButtonJS(backToStore);
            waitMethod();
            clickButtonJS(profile);
        }
        return booksNumber;
    }

    //Return a list for all books found assigned on the user with the details of each book
    public List getBookDetails(){
        int booksNumber = getProfileBooksNumber();
        List<WebElement> bookLinks = driver.findElements(booksList);
        List<Book> userBookList = new ArrayList<>();
        int rowsNumber = bookLinks.size();

        //Loop to get the details of each book
        for(int i = 0; i < booksNumber; i++){
            waitMethod();
            bookLinks = driver.findElements(booksList);
            Book openedBook = new Book();
            WebElement book = bookLinks.get(i-(i/rowsNumber)*rowsNumber);
            clickButtonJS(book);
            waitMethod();

            openedBook.setIsbn(getWebElement(isbn).getText());
            openedBook.setTitle(getWebElement(title).getText());
            openedBook.setSubTitle(getWebElement(subtitle).getText());
            openedBook.setAuthor(getWebElement(author).getText());
            openedBook.setPublisher(getWebElement(publisher).getText());
            openedBook.setPages(Integer.valueOf(getWebElement(totalPages).getText()));
            openedBook.setDescription(getWebElement(description).getText());
            openedBook.setWebsite(getWebElement(website).getText());

            userBookList.add(openedBook);
            clickButtonJS(backButton);

            if(i/(rowsNumber-1)>=1){
                if(driver.findElement(nextBtn).isEnabled()){
                    clickButtonJS(nextBtn);
                }
            }
        }
        return userBookList;
    }
}
