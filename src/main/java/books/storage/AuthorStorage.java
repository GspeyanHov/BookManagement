package books.storage;

import books.exception.AuthorNotFoundException;
import books.model.Author;
import books.model.Gender;

public class AuthorStorage {

    private static Author[] array = new Author[10];
    private static int size = 0;

    public void printAuthorByAuthorGender(Gender gender){
        boolean exists = false;
        for (int i = 0; i < size; i++) {
            if(array[i].getGender().equals(gender)){
                System.out.println(i + ". " + array[i]);
                exists = true;
            }
        }
        if(!exists){
            System.out.println("No such author with that gender!. Please try again! ");
        }
    }
    public void printAuthorByAuthorEmail(String email){
        boolean exists = false;
        for (int i = 0; i < size; i++) {
            if(array[i].getEmail().equals(email)){
                System.out.println(i + ". " + array[i]);
                exists = true;
            }
        }
        if(!exists){
            System.out.println("No such author with that email!. Please try again! ");
        }
    }
    public Author getByIndex(int index) throws AuthorNotFoundException {
        if (index >= size || index < 0) {
            throw new AuthorNotFoundException("Author with that " + index + "doesn't exist ");
        }
        return array[index];
    }

    private void extend() {
        Author[] authors = new Author[array.length + 10];
        for (int i = 0; i < array.length; i++) {
            authors[i] = array[i];
        }
        array = authors;
    }

    public void add(Author author) throws AuthorNotFoundException {
        if (array.length == size) {
            extend();
            throw new AuthorNotFoundException("Author with that " + author + "doesn't exist ");
        }
        array[size++] = author;
    }

    public void printArray() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + ". " + array[i]);
        }
    }
    public int getSize() {
        return size;
    }
}

