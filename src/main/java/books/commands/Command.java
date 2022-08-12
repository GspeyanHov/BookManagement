package books.commands;

public interface Command {

    int LOGOUT = 0;
    int ADD_BOOKS = 1;
    int PRINT_ALL_BOOKS = 2;
    int PRINT_BOOKS_BY_AUTHOR_NAME = 3;
    int PRINT_AUTHOR_BY_AUTHOR_GENDER = 4;
    int PRINT_AUTHOR_BY_AUTHOR_EMAIL = 5;
    int PRINT_BOOKS_BY_GENRE = 6;
    int PRINT_BOOKS_BY_PRICE_RANGE = 7;
    int ADD_AUTHOR = 8;
    int PRINT_ALL_AUTHORS = 9;

    int EXIT = 0;
    int LOGIN = 1;
    int REGISTER = 2;

    static void adminCommands() {
        System.out.println("Please input " + LOGOUT + " for exit ");
        System.out.println("Please input " + ADD_BOOKS + " for add books ");
        System.out.println("Please input " + PRINT_ALL_BOOKS + " for print all books ");
        System.out.println("Please input " + PRINT_BOOKS_BY_AUTHOR_NAME + " for print book by author name ");
        System.out.println("Please input " + PRINT_AUTHOR_BY_AUTHOR_GENDER + " for print author by author gender ");
        System.out.println("Please input " + PRINT_AUTHOR_BY_AUTHOR_EMAIL + " for print author by author email ");
        System.out.println("Please input " + PRINT_BOOKS_BY_GENRE + " for print book by genre ");
        System.out.println("Please input " + PRINT_BOOKS_BY_PRICE_RANGE + " for print book by price range ");
        System.out.println("Please input " + ADD_AUTHOR + " for add authors ");
        System.out.println("Please input " + PRINT_ALL_AUTHORS + " for print all authors ");
    }

    static void loginCommands() {
        System.out.println("Please input " + EXIT + " for exit ");
        System.out.println("Please input " + LOGIN + " for login ");
        System.out.println("Please input " + REGISTER + " for register ");
    }

    static void UserCommands() {
        System.out.println("Please input " + LOGOUT + " for exit ");
        System.out.println("Please input " + ADD_BOOKS + " for add books ");
        System.out.println("Please input " + PRINT_ALL_BOOKS + " for print all books ");
        System.out.println("Please input " + PRINT_BOOKS_BY_AUTHOR_NAME + " for print book by author name ");
        System.out.println("Please input " + PRINT_AUTHOR_BY_AUTHOR_GENDER + " for print author by author gender ");
        System.out.println("Please input " + PRINT_AUTHOR_BY_AUTHOR_EMAIL + " for print author by author email ");
        System.out.println("Please input " + PRINT_BOOKS_BY_GENRE + " for print book by genre ");
        System.out.println("Please input " + PRINT_BOOKS_BY_PRICE_RANGE + " for print book by price range ");
        System.out.println("Please input " + PRINT_ALL_AUTHORS + " for print all authors ");

    }
}
