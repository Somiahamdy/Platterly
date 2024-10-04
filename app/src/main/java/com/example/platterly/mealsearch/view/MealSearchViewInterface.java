package com.example.platterly.mealsearch.view;

import com.example.platterly.model.Meal;

import java.util.List;

public interface MealSearchViewInterface {
    public void setMeals(List<Meal> meals);
    public void showToast(String msg);
}
