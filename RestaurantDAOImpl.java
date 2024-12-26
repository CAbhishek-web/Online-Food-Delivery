package com.foodapp.DaoImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.foodapp.Dao.RestaurantDAO;
import com.foodapp.model.Restaurant;

public class RestaurantDAOImpl implements RestaurantDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/online_food_delivary";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static final String INSERT_RESTAURANT = "INSERT INTO restaurants(restaurant_id, name, cusinetype, delivary_time, isactive, ratings) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String FETCH_ALL_RESTAURANTS = "SELECT * FROM restaurants";
    private static final String FETCH_RESTAURANT_BY_ID = "SELECT * FROM restaurants WHERE restaurant_id=?";
    private static final String DELETE_RESTAURANT = "DELETE FROM restaurants WHERE restaurant_id=?";

    @Override
    public void insertRestaurant(Restaurant restaurant) {
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(INSERT_RESTAURANT)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            pstmt.setInt(1, restaurant.getRestaurantId());
            pstmt.setString(2, restaurant.getName());
            pstmt.setString(3, restaurant.getCuisineType());
            pstmt.setInt(4, restaurant.getDeliveryTime());
            pstmt.setBoolean(5, restaurant.isActive());
            pstmt.setInt(6, restaurant.getRatings());
            pstmt.execute();
            System.out.println("Restaurant inserted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Restaurant> fetchAllRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(FETCH_ALL_RESTAURANTS)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            while (rs.next()) {
                Restaurant restaurant = new Restaurant(
                        rs.getInt("restaurant_id"),
                        rs.getString("name"),
                        rs.getString("cusinetype"),
                        rs.getInt("delivary_time"),
                        rs.getBoolean("isactive"),
                        rs.getInt("ratings")
                );
                restaurants.add(restaurant);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restaurants;
    }

    @Override
    public Restaurant fetchRestaurantById(int restaurantId) {
        Restaurant restaurant = null;
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(FETCH_RESTAURANT_BY_ID)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            pstmt.setInt(1, restaurantId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                restaurant = new Restaurant(
                        rs.getInt("restaurant_id"),
                        rs.getString("name"),
                        rs.getString("cusinetype"),
                        rs.getInt("delivary_time"),
                        rs.getBoolean("isactive"),
                        rs.getInt("ratings")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restaurant;
    }

    @Override
    public void deleteRestaurant(int restaurantId) {
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(DELETE_RESTAURANT)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            pstmt.setInt(1, restaurantId);
            pstmt.execute();
            System.out.println("Restaurant deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

