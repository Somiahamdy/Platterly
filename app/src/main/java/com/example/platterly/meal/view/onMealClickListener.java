package com.example.platterly.meal.view;

import com.example.platterly.model.Meal;
import com.example.platterly.model.PlanMeal;

public interface onMealClickListener {
    public void onMealAddFavListener(Meal meal);
    public void onMealRemoveFavListener(Meal meal);
    public void onOpenCalendar(PlanMeal planMeal);
}
