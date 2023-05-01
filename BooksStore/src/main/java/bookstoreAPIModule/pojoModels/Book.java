package bookstoreAPIModule.pojoModels;

import lombok.Getter;
import lombok.Setter;

/*This class is to Create Pojo object for each book.
* It uses Lombok to create Setters & Getters.
* It implements Comparable class to sort the objects according to ISBN number to
* easily compare between them.*/
@Getter @Setter
public class Book implements Comparable<Book>{
    String isbn;
    String title;
    String subTitle;
    String author;
    String publish_date;
    String publisher;
    int pages;
    String description;
    String website;

    //Sort with the ISBN Numbers descendingly
    @Override
    public int compareTo(Book o) {
        return(o.isbn.compareTo(isbn));
    }

}
