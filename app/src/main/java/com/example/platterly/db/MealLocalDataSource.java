package com.example.platterly.db;

import androidx.lifecycle.LiveData;

import com.example.platterly.model.Meal;

import java.util.List;

public interface MealLocalDataSource {
    public void inserMeal(Meal meal);
    public void deleteMeal(Meal meal);
    public LiveData<List<Meal>> getAllStoredMeals();
    public LiveData<Meal> getMealDetails(String mealId);
}
