package com.example.platterly.categories.view;

import com.example.platterly.model.Category;

import java.util.List;

public interface CatViewInterface {
    void setCategories(List<Category> categories);
    void showErrorResponse(String msgError);
    void showToast(String txt);
}
