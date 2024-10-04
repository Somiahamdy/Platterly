package com.example.platterly.model;

import androidx.lifecycle.LiveData;

import com.example.platterly.network.NetworkCallBack;
import com.example.platterly.network.RMealNetworkCallBack;

import java.util.List;

public interface MealRepository {
    public void getRandomMeal(RMealNetworkCallBack networkCallBack);
    public void getMealDetails(RMealNetworkCallBack networkCallBack,String s);
    public void getMealSearch(RMealNetworkCallBack networkCallBack,String s);
    public void insertMealToFav(Meal meal);
    public void removeMealFromFav(Meal meal);
    public LiveData<List<Meal>> getStoredFavMeals();

    public void getMealSearchCountry(RMealNetworkCallBack networkCallBack,String s);
    public void getMealSearchIngredient(RMealNetworkCallBack networkCallBack,String s);
    public void getMealSearchName(RMealNetworkCallBack networkCallBack,String s);

    public void insertMealToPlan(PlanMeal planMeal);
    public void RemoveMealFromPlan(PlanMeal planMeal);
    public LiveData<List<PlanMeal>> getMealByDate(String date);
}
