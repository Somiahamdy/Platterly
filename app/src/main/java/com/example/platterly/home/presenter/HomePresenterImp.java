package com.example.platterly.home.presenter;

import com.example.platterly.home.view.HomeViewInterface;
import com.example.platterly.model.Meal;
import com.example.platterly.model.MealRepository;
import com.example.platterly.network.RMealNetworkCallBack;

import java.util.List;

public class HomePresenterImp implements HomePresenter, RMealNetworkCallBack {
    private MealRepository mealrepo;
    private HomeViewInterface ihomeView;

    public HomePresenterImp(MealRepository _mealrepo,HomeViewInterface _ihomeView){
        this.mealrepo=_mealrepo;
        this.ihomeView=_ihomeView;
    }

    @Override
    public void getRandomMeal() {
        mealrepo.getRandomMeal(this);
    }

    @Override
    public void onRMealSuccessfulResponse(List<Meal> meals) {
      ihomeView.setSuggestionMeal(meals);
    }

    @Override
    public void onRMealFailureResponse(String errorMessage) {

    }
}
