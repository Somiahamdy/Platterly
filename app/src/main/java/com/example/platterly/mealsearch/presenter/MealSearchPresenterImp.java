package com.example.platterly.mealsearch.presenter;

import android.widget.Toast;

import com.example.platterly.meal.view.MealViewInterface;
import com.example.platterly.mealsearch.view.MealSearchViewInterface;
import com.example.platterly.mealsearch.view.SearchFragment;
import com.example.platterly.model.Meal;
import com.example.platterly.model.MealRepository;
import com.example.platterly.network.RMealNetworkCallBack;

import java.util.ArrayList;
import java.util.List;

public class MealSearchPresenterImp implements MealSearchPresenter , RMealNetworkCallBack {
    MealRepository mealRepo;
    MealSearchViewInterface imealsearchView;

    public MealSearchPresenterImp(MealRepository _mealrepo, MealSearchViewInterface _imealView){
        this.mealRepo = _mealrepo;
        this.imealsearchView = _imealView;
    }
    @Override
    public void getMealSearch(String s) {
        mealRepo.getMealSearch(this,s);
    }

    @Override
    public void getMealSearchcountry(String s) {
        mealRepo.getMealSearchCountry(this,s);
    }

    @Override
    public void getMealSearchIng(String s) {
        mealRepo.getMealSearchIngredient(this,s);
    }

    @Override
    public void getMealSearchname(String s) {
        mealRepo.getMealSearchName(this,s);
    }

    @Override
    public void onRMealSuccessfulResponse(List<Meal> meals) {
        if(meals.size()==0){
            meals=new ArrayList<>();
            imealsearchView.showToast("Not Found!");
        }else{
            imealsearchView.setMeals(meals);

        }

    }

    @Override
    public void onRMealFailureResponse(String errorMessage) {
      imealsearchView.setMeals(new ArrayList<>());
        //Toast.makeText(this,"Not Found",Toast.LENGTH_SHORT).show();
        imealsearchView.showToast(errorMessage);
    }
}
