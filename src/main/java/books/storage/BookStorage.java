package books.storage;

import books.model.Book;

public class BookStorage {

    private static Book[] array = new Book[10];
    private static int size = 0;

    public void printBookByAuthorName(String name, String surName){
        boolean exists = false;
        for (int i = 0; i < size; i++) {
            if (array[i].getAuthor().getName().equals(name) && array[i].getAuthor().getSurname().equals(surName)){
                System.out.println(i + ". " + array[i]);
                exists = true;
            }
        }
        if(!exists){
            System.out.println("No such author with that name or surname!. Please try again! ");
        }
    }

    public void printBookByGenre(String genre) {
        boolean exists = false;
        for (int i = 0; i < size; i++) {
            if (array[i].getGenre().equals(genre)) {
                System.out.println(i + ". " + array[i]);
                exists = true;
            }
        }
        if(!exists){
            System.out.println("No such book in that price range");
        }
    }

    public void printBooksByPriceRange(double minPrice, double maxPrice) {
        boolean exists = false;
        for (int i = 0; i < size; i++) {
            if (array[i].getPrice() >= minPrice && array[i].getPrice() < maxPrice) {
                System.out.println(i + ". " + array[i]);
                exists = true;
            }
        }
        if(!exists){
            System.out.println("No such book in that price range");
        }
    }

    private void extend() {
        Book[] book = new Book[array.length + 10];
        for (int i = 0; i < array.length; i++) {
            book[i] = array[i];
        }
        array = book;
    }

    public void add(Book book) {
        if (array.length == size) {
            extend();
        }
        array[size++] = book;
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
