package books.manager;

import books.model.Book;
import books.model.User;
import books.model.UserRole;
import connection.DbConnectionProvider;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private static final Connection CONNECTION = DbConnectionProvider.getInstance().getConnection();
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void addBook(Book book) {
        String sql = "insert into book_managment.book(title,author_id,price,count,genre,added_date,description) VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement ps = CONNECTION.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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

    public void addUser(User user) {
        String sql = "insert into book_managment.user(name,surname,email,password,user_role,profile_pic) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getUserRole().name());
            preparedStatement.setString(6, user.getProfilePic());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                user.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List <User> getAll(){
        String sql = "select * from book_managment.user";
        List<User> usersList = new ArrayList<>();
        try {
            Statement ps = CONNECTION.createStatement();
            ResultSet resultSet = ps.executeQuery(sql);
            while (resultSet.next()){
                usersList.add(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    private User getUserFromResultSet(ResultSet resultSet) {
        try {
            return User.builder()
                    .Id(Integer.parseInt(resultSet.getString("id")))
                    .name(resultSet.getString("name"))
                    .surname(resultSet.getString("surname"))
                    .email((resultSet.getString("email")))
                    .password((resultSet.getString("password")))
                    .userRole(UserRole.valueOf(resultSet.getString("user_role")))
                    .profilePic(resultSet.getString("profile_pic"))
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByEmailPassword(String email, String password) {

        String sql = "Select * from book_managment.user where email = ? and password = ?";
        try {
            PreparedStatement ps = CONNECTION.prepareStatement(sql);
            ps.setString(1,email);
            ps.setString(2,password);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
            return getUserFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public User getUserByEmail(String email) {
        String sql = "Select * from book_managment.user where email = ?";
        try {
            PreparedStatement ps = CONNECTION.prepareStatement(sql);
            ps.setString(1,email);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteUserById(int userId) {

        String sql = "delete from book_managment.user where id = " + userId;
        try {
            Statement statement = CONNECTION.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getById(int userId) {
        String sql = "Select * from book_managment.user where id = " + userId;
        try {
            Statement statement = CONNECTION.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void edit(User user) {
        String sql = "update book_managment.user set name = ?,surname = ?,email = ?,password = ?,user_role = ?," +
                "profile_pic = ? where id = ?";
        try {
            PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5,user.getUserRole().name());
            preparedStatement.setString(6,user.getProfilePic());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
