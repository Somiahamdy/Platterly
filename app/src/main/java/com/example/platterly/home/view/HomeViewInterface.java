package com.example.platterly.home.view;

import com.example.platterly.model.Meal;

import java.util.List;

public interface HomeViewInterface {
    public void setSuggestionMeal(List<Meal> meals);
    public void showErrors(String error);
}
