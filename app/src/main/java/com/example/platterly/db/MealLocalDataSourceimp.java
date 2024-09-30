package com.example.platterly.db;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.platterly.model.Meal;

import java.util.List;

public class MealLocalDataSourceimp implements MealLocalDataSource{
    private Context context;
    private MealDAO mealDAO;
    private LiveData<List<Meal>> storedMeals;
    private LiveData<Meal> mealDetails;
    private static MealLocalDataSourceimp mealLocalDataSource=null;
    MealLocalDataSourceimp(Context _context){
        this.context = _context;
        AppDataBase db = AppDataBase.getInstance(context.getApplicationContext());
        mealDAO = db.getMealDAO();
        storedMeals = mealDAO.getAllMeals();
        //mealDetails=mealDAO.getMealById()
    }

    public static  MealLocalDataSourceimp getInstance(Context _context){
        if(mealLocalDataSource==null){
            mealLocalDataSource = new MealLocalDataSourceimp(_context);
        }
        return mealLocalDataSource;

    }
    @Override
    public void inserMeal(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {

               mealDAO.insert(meal);
                //Log.d("MealLocalDataSourceimp", "Meal inserted with result: " + result);
            }
        }).start();
    }

    @Override
    public void deleteMeal(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.delete(meal);
            }
        }).start();
    }

    @Override
    public LiveData<List<Meal>> getAllStoredMeals() {
        return mealDAO.getAllMeals();
    }

    @Override
    public LiveData<Meal> getMealDetails(String mealId) {
        return mealDAO.getMealById(mealId);
    }
}
