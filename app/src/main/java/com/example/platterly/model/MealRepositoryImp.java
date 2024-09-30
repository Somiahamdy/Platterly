package com.example.platterly.model;

import androidx.lifecycle.LiveData;

import com.example.platterly.db.MealLocalDataSource;
import com.example.platterly.network.CatRemoteDataSource;
import com.example.platterly.network.NetworkCallBack;
import com.example.platterly.network.RMealNetworkCallBack;
import com.example.platterly.network.RMealRemoteDataSource;

import java.util.List;

public class MealRepositoryImp implements MealRepository{
    private RMealRemoteDataSource mealRemoteDataSource;
    private static MealRepositoryImp mealRepositoryImp=null;
    private MealLocalDataSource mealLocalDataSource;

    MealRepositoryImp(RMealRemoteDataSource rMealRemoteDataSource , MealLocalDataSource _mealLocalDataSource){
        this.mealLocalDataSource=_mealLocalDataSource;
        this.mealRemoteDataSource= rMealRemoteDataSource;
    }

    public static MealRepositoryImp getInstance(RMealRemoteDataSource rMealRemoteDataSource,MealLocalDataSource _mealLocalDataSource){
        if(mealRepositoryImp==null){
            mealRepositoryImp=new MealRepositoryImp(rMealRemoteDataSource,_mealLocalDataSource);
        }
        return mealRepositoryImp;
    }


    @Override
    public void getRandomMeal(RMealNetworkCallBack networkCallBack) {
        mealRemoteDataSource.makeNetworkCall(networkCallBack);
    }

    @Override
    public void getMealDetails(RMealNetworkCallBack networkCallBack,String s) {
        mealRemoteDataSource.dmakeNetworkCall(networkCallBack,s);
    }

    @Override
    public void getMealSearch(RMealNetworkCallBack networkCallBack, String s) {
        mealRemoteDataSource.cmakeNetworkCall(networkCallBack,s);
    }

    @Override
    public void insertMealToFav(Meal meal) {
        mealLocalDataSource.inserMeal(meal);
    }

    @Override
    public void removeMealFromFav(Meal meal) {
        mealLocalDataSource.deleteMeal(meal);
    }

    @Override
    public LiveData<List<Meal>> getStoredFavMeals() {
        return mealLocalDataSource.getAllStoredMeals();
    }

    @Override
    public void getMealSearchCountry(RMealNetworkCallBack networkCallBack, String s) {
        mealRemoteDataSource.amakeNetworkCall(networkCallBack,s);
    }

    @Override
    public void getMealSearchIngredient(RMealNetworkCallBack networkCallBack, String s) {
       mealRemoteDataSource.imakeNetworkCall(networkCallBack,s);
    }

    @Override
    public void getMealSearchName(RMealNetworkCallBack networkCallBack, String s) {
        mealRemoteDataSource.smakeNetworkCall(networkCallBack,s);
    }
}
