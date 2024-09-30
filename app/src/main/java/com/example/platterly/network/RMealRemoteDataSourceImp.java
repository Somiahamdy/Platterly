package com.example.platterly.network;

import android.util.Log;

import com.example.platterly.model.CatResponse;
import com.example.platterly.model.Meal;
import com.example.platterly.model.MealResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RMealRemoteDataSourceImp implements RMealRemoteDataSource{
    private static String RMEAL_URL="https://www.themealdb.com/api/json/v1/1/";
    NetworkService networkservice;
    private static RMealRemoteDataSourceImp rmealRemoteDataSourceImp = null;
    private static String TAG="rmealremote";

    RMealRemoteDataSourceImp(){
        Retrofit retrofit= new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(RMEAL_URL).build();
        networkservice = retrofit.create(NetworkService.class);  //to get json data

    }

    public static RMealRemoteDataSourceImp getInstance(){
        if(rmealRemoteDataSourceImp==null){
            rmealRemoteDataSourceImp=new RMealRemoteDataSourceImp();
        }
        return  rmealRemoteDataSourceImp;
    }
    @Override
    public void makeNetworkCall(RMealNetworkCallBack networkCallBack) {
        Call<MealResponse> call= networkservice.getRMeal();
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG, "onResponse: successful"+response.body());
                    networkCallBack.onRMealSuccessfulResponse(response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                Log.i(TAG, "onFailure: failed"+throwable.getMessage());
                networkCallBack.onRMealFailureResponse(throwable.getMessage());
            }
        });
    }

    @Override
    public void dmakeNetworkCall(RMealNetworkCallBack networkCallBack, String s) {
        Call<MealResponse> call= networkservice.getMealDetails(s);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG, "onResponse: successful"+response.body());
                    networkCallBack.onRMealSuccessfulResponse(response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                Log.i(TAG, "onFailure: failed"+throwable.getMessage());
                networkCallBack.onRMealFailureResponse(throwable.getMessage());
            }
        });
    }

    @Override
    public void cmakeNetworkCall(RMealNetworkCallBack networkCallBack, String s) {
        Call<MealResponse> call= networkservice.getMealByCategory(s);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG, "onResponse: successful"+response.body());
                    networkCallBack.onRMealSuccessfulResponse(response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                Log.i(TAG, "onFailure: failed"+throwable.getMessage());
                networkCallBack.onRMealFailureResponse(throwable.getMessage());
            }
        });
    }

    @Override
    public void imakeNetworkCall(RMealNetworkCallBack networkCallBack, String s) {
        Call<MealResponse> call= networkservice.getMealByIngredient(s);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG, "onResponse: successful"+response.body());
                    networkCallBack.onRMealSuccessfulResponse(response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                Log.i(TAG, "onFailure: failed"+throwable.getMessage());
                networkCallBack.onRMealFailureResponse(throwable.getMessage());
            }
        });
    }

    @Override
    public void amakeNetworkCall(RMealNetworkCallBack networkCallBack, String s) {
        Call<MealResponse> call= networkservice.getMealByCountry(s);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG, "onResponse: successful"+response.body());
                    networkCallBack.onRMealSuccessfulResponse(response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                Log.i(TAG, "onFailure: failed"+throwable.getMessage());
                networkCallBack.onRMealFailureResponse(throwable.getMessage());
            }
        });
    }

    @Override
    public void smakeNetworkCall(RMealNetworkCallBack networkCallBack, String s) {
        Call<MealResponse> call= networkservice.getMealByName(s);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG, "onResponse: successful"+response.body());
                    networkCallBack.onRMealSuccessfulResponse(response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                Log.i(TAG, "onFailure: failed"+throwable.getMessage());
                networkCallBack.onRMealFailureResponse(throwable.getMessage());
            }
        });
    }
}
