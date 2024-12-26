package com.foodapp.model;

public class Restaurant {
    private int restaurantId;
    private String name;
    private String cuisineType;
    private int deliveryTime;
    private boolean isActive;
    private int ratings;

    public Restaurant(int restaurantId, String name, String cuisineType, int deliveryTime, boolean isActive, int ratings) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.cuisineType = cuisineType;
        this.deliveryTime = deliveryTime;
        this.isActive = isActive;
        this.ratings = ratings;
    }

    // Getters and Setters
    public int getRestaurantId() { return restaurantId; }
    public void setRestaurantId(int restaurantId) { this.restaurantId = restaurantId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCuisineType() { return cuisineType; }
    public void setCuisineType(String cuisineType) { this.cuisineType = cuisineType; }

    public int getDeliveryTime() { return deliveryTime; }
    public void setDeliveryTime(int deliveryTime) { this.deliveryTime = deliveryTime; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    public int getRatings() { return ratings; }
    public void setRatings(int ratings) { this.ratings = ratings; }
}

