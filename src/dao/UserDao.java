package dao;

import core.Database;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {
    private Connection connection;

    public UserDao() {
        this.connection = Database.getInstance();
    }

    public User findByLogin(String mail, String password) {
        User user = new User();

        String query = "select * from user where mail=? and password=?";
        try {
            PreparedStatement pr = this.connection.prepareCall(query);
            pr.setString(1, mail);
            pr.setString(2, password);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                user = this.match(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public ArrayList<User> findAll() {
        ArrayList<User> users = new ArrayList<>();
        try {
            ResultSet rs = this.connection.createStatement().executeQuery("SELECT * FROM user");
            while (rs.next()) {
                users.add(this.match(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public User match(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setEmail(rs.getString("mail"));
        user.setPassword(rs.getString("password"));
        user.setName(rs.getString("name"));
        return user;
    }
}
