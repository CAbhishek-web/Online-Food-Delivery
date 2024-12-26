package com.foodapp.DaoImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.foodapp.Dao.MenuDAO;
import com.foodapp.model.Menu;

public class MenuDAOImpl implements MenuDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/online_food_delivary";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static final String INSERT_MENU = "INSERT INTO menus(menu_id, restaurant_id, item_name, description, price, isavailable) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String FETCH_ALL_MENUS = "SELECT * FROM menus";
    private static final String FETCH_MENU_BY_ID = "SELECT * FROM menus WHERE menu_id=?";
    private static final String DELETE_MENU = "DELETE FROM menus WHERE menu_id=?";

    @Override
    public void insertMenu(Menu menu) {
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(INSERT_MENU)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            pstmt.setInt(1, menu.getMenuId());
            pstmt.setInt(2, menu.getRestaurantId());
            pstmt.setString(3, menu.getItemName());
            pstmt.setString(4, menu.getDescription());
            pstmt.setInt(5, menu.getPrice());
            pstmt.setBoolean(6, menu.isAvailable());
            pstmt.execute();
            System.out.println("Menu inserted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Menu> fetchAllMenus() {
        List<Menu> menus = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(FETCH_ALL_MENUS)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            while (rs.next()) {
                Menu menu = new Menu(
                        rs.getInt("menu_id"),
                        rs.getInt("restaurant_id"),
                        rs.getString("item_name"),
                        rs.getString("description"),
                        rs.getInt("price"),
                        rs.getBoolean("isavailable")
                );
                menus.add(menu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menus;
    }

    @Override
    public Menu fetchMenuById(int menuId) {
        Menu menu = null;
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(FETCH_MENU_BY_ID)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            pstmt.setInt(1, menuId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                menu = new Menu(
                        rs.getInt("menu_id"),
                        rs.getInt("restaurant_id"),
                        rs.getString("item_name"),
                        rs.getString("description"),
                        rs.getInt("price"),
                        rs.getBoolean("isavailable")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menu;
    }

    @Override
    public void deleteMenu(int menuId) {
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(DELETE_MENU)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            pstmt.setInt(1, menuId);
            pstmt.execute();
            System.out.println("Menu deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

