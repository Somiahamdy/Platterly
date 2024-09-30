package com.example.platterly.meal.presenter;

import com.example.platterly.model.Meal;

public interface MealPresenter {
    public void getMealDetails(String s);
    public void insertMeal(Meal meal);
    public void deleteMeal(Meal meal);
}
