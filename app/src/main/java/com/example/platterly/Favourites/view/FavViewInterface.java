package com.example.platterly.Favourites.view;

import com.example.platterly.model.Meal;

import java.util.List;

public interface FavViewInterface {
    public void showMeals(List<Meal> meals);
    public void onDataChanged(List<Meal> meal);
    public void notifyAdapter();

}
