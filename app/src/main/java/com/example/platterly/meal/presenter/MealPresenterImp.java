package com.example.platterly.meal.presenter;

import com.example.platterly.meal.view.MealViewInterface;
import com.example.platterly.model.Meal;
import com.example.platterly.model.MealRepository;
import com.example.platterly.network.RMealNetworkCallBack;

import java.util.List;

public class MealPresenterImp implements RMealNetworkCallBack , MealPresenter {
    MealRepository mealRepo;
    MealViewInterface imealView;

    public MealPresenterImp(MealRepository _mealrepo, MealViewInterface _imealView){
        this.mealRepo = _mealrepo;
        this.imealView = _imealView;
    }
    @Override
    public void getMealDetails(String s) {
        mealRepo.getMealDetails(this,s);
    }

    @Override
    public void insertMeal(Meal meal) {
       mealRepo.insertMealToFav(meal);
    }

    @Override
    public void deleteMeal(Meal meal) {
        mealRepo.removeMealFromFav(meal);
    }

    @Override
    public void onRMealSuccessfulResponse(List<Meal> meals) {
        imealView.setMeals(meals);
    }

    @Override
    public void onRMealFailureResponse(String errorMessage) {

    }
}
