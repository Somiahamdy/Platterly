package com.example.platterly.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.platterly.model.Meal;
import com.example.platterly.model.PlanMeal;

import java.util.List;
@Dao
public interface MealDAO {
    @Query("SELECT * FROM meals")
    LiveData<List<Meal>> getAllMeals();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Meal meal);

    @Delete
    void delete(Meal meal);
    @Query("SELECT * FROM meals")
    List<Meal> getAllMealsDirect();

    @Query("SELECT * FROM meals WHERE idMeal = :mealId LIMIT 1")
    LiveData<Meal> getMealById(String mealId);

    //insert meal to plan
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(PlanMeal planMeal);

    //delete from plan
    @Delete
    void delete(PlanMeal planMeal);

    @Query("SELECT * FROM plannedmeals WHERE date = :date")
    LiveData<List<PlanMeal>> getMealByDate(String date);
}
