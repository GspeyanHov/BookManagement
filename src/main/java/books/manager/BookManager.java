package books.manager;

import books.model.Book;
import connection.DbConnectionProvider;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BookManager {

    private final Connection connection = DbConnectionProvider.getInstance().getConnection();

    private final AuthorManager authorManager = new AuthorManager();
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void add(Book book) {
        String sql = "insert into book_managment.book(title,author_id,price,count,genre,added_date,description) VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, book.getTitle());
            ps.setInt(2, book.getAuthor().getId());
            ps.setDouble(3, book.getPrice());
            ps.setInt(4, book.getCount());
            ps.setString(5, book.getGenre());
            ps.setString(6, sdf.format(book.getAddedDate()));
            ps.setString(7, book.getDescription());
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                book.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Book> getAllBooks() {
        String sql = "select * from book_managment.book";
        List<Book> bookList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                bookList.add(getBookFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public Book getById(int id) {
        String sql = "select * from book_managment.book where id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return getBookFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Book getBookFromResultSet(ResultSet resultSet) {
        try {
            return Book.builder()
                    .id(resultSet.getInt("id"))
                    .title(resultSet.getString("title"))
                    .author(authorManager.getById(resultSet.getInt("author_id")))
                    .price(Double.parseDouble(resultSet.getString("price")))
                    .count(Integer.parseInt(resultSet.getString("count")))
                    .genre(resultSet.getString("genre"))
                    .addedDate(resultSet.getString("added_date") == null ? null : sdf.parse(resultSet.getString("added_date")))
                    .description(resultSet.getString("description"))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteBookById(int id) {
        String sql = "delete from book_managment.book where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void edit(Book book) {
        String sql = "update book_managment.book set title = ?,author_id = ?,price = ?,count = ?,genre = ?," +
                "added_date = ?,description = ? where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getAuthor().getId());
            preparedStatement.setDouble(3, book.getPrice());
            preparedStatement.setInt(4, book.getCount());
            preparedStatement.setString(5, book.getGenre());
            preparedStatement.setString(6, sdf.format(book.getAddedDate()));
            preparedStatement.setString(7, book.getDescription());
            preparedStatement.setInt(8,book.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
