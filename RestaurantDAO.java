package com.foodapp.Dao;

import java.util.List;

import com.foodapp.model.Restaurant;

public interface RestaurantDAO {
    void insertRestaurant(Restaurant restaurant);
    List<Restaurant> fetchAllRestaurants();
    Restaurant fetchRestaurantById(int restaurantId);
    void deleteRestaurant(int restaurantId);
}

