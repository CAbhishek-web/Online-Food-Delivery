package com.foodapp.Dao;

import java.util.List;

import com.foodapp.model.Menu;

public interface MenuDAO {
    void insertMenu(Menu menu);
    List<Menu> fetchAllMenus();
    Menu fetchMenuById(int menuId);
    void deleteMenu(int menuId);
}

