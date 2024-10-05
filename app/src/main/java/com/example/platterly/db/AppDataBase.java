package com.example.platterly.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.platterly.model.Meal;
import com.example.platterly.model.PlanMeal;
import com.example.platterly.plan.view.Converters;

@Database(entities = {Meal.class, PlanMeal.class}, version = 6)
@TypeConverters({Converters.class})
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance = null;
    public abstract MealDAO getMealDAO();
    public static synchronized AppDataBase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,"mealdb")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}