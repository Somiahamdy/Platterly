package com.example.platterly.network;

import com.example.platterly.model.CatResponse;
import com.example.platterly.model.CountryResponse;
import com.example.platterly.model.Meal;
import com.example.platterly.model.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkService {
    @GET("categories.php")
    Call<CatResponse> getCategories();

    @GET("random.php")
    Call<MealResponse> getRMeal();

    @GET("lookup.php")
    Call<MealResponse> getMealDetails(@Query("i") String mealID);

    @GET("filter.php")
    Call<MealResponse> getMealByCategory(@Query("c") String mealCat);

    @GET("filter.php")
    Call<MealResponse> getMealByCountry(@Query("a") String mealCountry);

    @GET("filter.php")
    Call<MealResponse> getMealByIngredient(@Query("i") String mealingredient);

    @GET("search.php")
    Call<MealResponse> getMealByName(@Query("s") String mealName);

    @GET("list.php")
    Call<CountryResponse> getCountries(@Query("a") String country);

}
