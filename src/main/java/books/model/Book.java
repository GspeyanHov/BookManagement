package books.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private String title;
    private Author author;
    private double price;
    private int count;
    private String genre;
    private User registeredUser;
    private Date addedDate;


}
