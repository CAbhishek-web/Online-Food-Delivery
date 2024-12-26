package com.foodapp.DaoImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.foodapp.Dao.UserDAO;
import com.foodapp.model.User;

public class UserDaoImpl implements UserDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/online_food_delivary";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static final String INSERT_USER = "INSERT INTO users(username,password,email) VALUES(?,?,?)";
    private static final String FETCH_ALL_USERS = "SELECT * FROM users";
    private static final String FETCH_USER_BY_ID = "SELECT * FROM users WHERE username=? and password = ?";
    private static final String DELETE_USER = "DELETE FROM users WHERE user_id=?";
    int status;

    @Override
    public int insertUser(User user) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement psmt = con.prepareStatement(INSERT_USER);
            psmt.setString(1, user.getUsername());
            psmt.setString(2, user.getPassword());
            psmt.setString(3, user.getEmail());
            status = psmt.executeUpdate();
            System.out.println("User inserted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<User> fetchAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(FETCH_ALL_USERS)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            while (rs.next()) {
                User user = new User(rs.getString(1), rs.getString(2), rs.getString(3));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User fetchUserById(String username, String password) {
        User user = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement psmt = con.prepareStatement(FETCH_USER_BY_ID);
            psmt.setString(1, username);
            psmt.setString(2, password);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString(1), rs.getString(2), rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void deleteUser(int userId) {
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement psmt = con.prepareStatement(DELETE_USER)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            psmt.setInt(1, userId);
            psmt.execute();
            System.out.println("User deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

