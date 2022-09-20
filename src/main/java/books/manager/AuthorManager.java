package books.manager;

import books.model.Author;

import books.model.Book;
import connection.DbConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorManager {

    private final static Connection CONNECTION = DbConnectionProvider.getInstance().getConnection();


    public void addAuthor(Author author) throws SQLException {
        String sql = "insert into book_managment.author(name,surname,email,age) VALUES(?,?,?,?) ";
        PreparedStatement ps = CONNECTION.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, author.getName());
        ps.setString(2, author.getSurname());
        ps.setString(3, author.getEmail());
        ps.setInt(4, author.getAge());
        ps.executeUpdate();
        ResultSet resultSet = ps.getGeneratedKeys();
        if (resultSet.next()) {
            int id = resultSet.getInt(1);
            author.setId(id);
        }

    }

    public List<Author> getAll() {
        String sql = "Select * from book_managment.author";
        List<Author> authorList = new ArrayList<Author>();
        try {
            Statement statement = CONNECTION.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                authorList.add(getAuthorFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorList;
    }

    public Author getById(int id) {
        String sql = "Select * from book_managment.author where id = " + id; //nuyn banery stex kanes
        try {
            Statement statement = CONNECTION.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return getAuthorFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Author getAuthorFromResultSet(ResultSet resultSet) {
        try {
            return Author.builder()
                    .id(Integer.parseInt(resultSet.getString("id")))
                    .name(resultSet.getString("name"))
                    .surname(resultSet.getString("surname"))
                    .email(resultSet.getString("email"))
                    .age(Integer.parseInt(resultSet.getString("age")))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAuthorById(int authorId) {
        String sql = "delete from book_managment.author where id = " + authorId;
        try {
            Statement statement = CONNECTION.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void edit(Author author) {
        String sql = "update book_managment.author set name = ?,surname = ?,email = ?,age = ? where id = ?";
        try {
            PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, author.getName());
            preparedStatement.setString(2, author.getSurname());
            preparedStatement.setString(3, author.getEmail());
            preparedStatement.setInt(4, author.getAge());
            preparedStatement.setInt(5,author.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

