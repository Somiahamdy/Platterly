package com.example.platterly.network;

import com.example.platterly.model.Category;

import java.util.List;

public interface NetworkCallBack {
    public void onCatSuccessfulResponse(List<Category> categories);
    public void onCatFailureResponse(String errorMessage);
}
