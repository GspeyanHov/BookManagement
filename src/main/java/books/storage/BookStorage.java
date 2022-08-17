package books.storage;

import books.model.Book;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class BookStorage {

    private static Book[] array = new Book[10];
    private static int size = 0;

    public static void downloadBookExcel(String fileDir) throws IOException {
        File directory = new File(fileDir);
        if(directory.isFile()){
            throw new RuntimeException("fileDir must be a directory ");
        }
        File excelFile = new File(fileDir,"books " + System.currentTimeMillis() + ".xlsx");
        excelFile.createNewFile();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("book");
        Row headerRow = sheet.createRow(0);
        Cell titleCell = headerRow.createCell(0);
        titleCell.setCellValue("title");
        Cell authorCell = headerRow.createCell(1);
        authorCell.setCellValue("author");
        Cell priceCell = headerRow.createCell(2);
        priceCell.setCellValue("price");
        Cell countCell = headerRow.createCell(3);
        countCell.setCellValue("count");
        Cell genreCell = headerRow.createCell(4);
        genreCell.setCellValue("genre");

        for (int i = 0; i < size; i++) {
            Book book = array[i];
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(book.getTitle());
            row.createCell(1).setCellValue(String.valueOf(book.getAuthor()));
            row.createCell(2).setCellValue(book.getPrice());
            row.createCell(3).setCellValue(book.getCount());
            row.createCell(4).setCellValue(book.getGenre());
        }
        workbook.write(new FileOutputStream(excelFile));
        System.out.println("file successfully created ");
    }

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
