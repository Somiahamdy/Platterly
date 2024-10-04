package com.example.platterly.db;

import androidx.lifecycle.LiveData;

import com.example.platterly.model.Meal;
import com.example.platterly.model.PlanMeal;

import java.util.List;

public interface MealLocalDataSource {
    public void inserMeal(Meal meal);
    public void deleteMeal(Meal meal);
    public LiveData<List<Meal>> getAllStoredMeals();
    public LiveData<Meal> getMealDetails(String mealId);
    public void InsertMealToPlan(PlanMeal planMeal);
    public void deleteMealFromPlan(PlanMeal planMeal);
    public LiveData<List<PlanMeal>> getMealByDate(String date);
}
