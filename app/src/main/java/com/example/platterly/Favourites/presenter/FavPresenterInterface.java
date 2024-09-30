package com.example.platterly.Favourites.presenter;

import android.content.Context;

import com.example.platterly.model.Meal;

public interface FavPresenterInterface {
    public void getFavMeals(Context c);
    public void deleteMeal(Meal meal);
}
