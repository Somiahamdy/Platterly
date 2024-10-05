package com.example.platterly.Favourites.presenter;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.example.platterly.Favourites.view.FavViewInterface;
import com.example.platterly.model.Meal;
import com.example.platterly.model.MealRepository;

import java.util.List;

public class FavPresenterImp implements FavPresenterInterface{
    private MealRepository mealrepo;
    private FavViewInterface ifavView;

    public FavPresenterImp(MealRepository repo,FavViewInterface _ifavView){
        this.ifavView=_ifavView;
        this.mealrepo=repo;
    }
    @Override
    public void getFavMeals(Context c) {
        mealrepo.getStoredFavMeals().observe((LifecycleOwner) c, new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {

                ifavView.onDataChanged(meals);
            }
        });
    }

    @Override
    public void deleteMeal(Meal meal) {
       mealrepo.removeMealFromFav(meal);
       ifavView.notifyAdapter();
    }
}
