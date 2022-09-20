package books.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private int id;
    private String title;
    private Author author;
    private double price;
    private int count;
    private String genre;
    private String description;
    private Date addedDate;
}
