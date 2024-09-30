package com.example.platterly.model;

import com.example.platterly.network.NetworkCallBack;

import java.util.List;

public interface CatRepository {
    public void getAllCategories(NetworkCallBack networkCallBack);
}
