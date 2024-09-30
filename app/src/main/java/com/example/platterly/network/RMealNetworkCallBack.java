package com.example.platterly.network;

import com.example.platterly.model.Category;
import com.example.platterly.model.Meal;

import java.util.List;

public interface RMealNetworkCallBack {
    public void onRMealSuccessfulResponse(List<Meal> meals);
    public void onRMealFailureResponse(String errorMessage);
}
