package com.example.platterly.categories.presenter;

import com.example.platterly.categories.view.CatViewInterface;
import com.example.platterly.model.CatRepository;
import com.example.platterly.model.Category;
import com.example.platterly.network.NetworkCallBack;

import java.util.List;

public class CatPresenterImp implements CatPresenter, NetworkCallBack {
    private CatRepository catrepo;
    private CatViewInterface icatView;
    // private ProductClient client;
    public  CatPresenterImp(CatRepository  r,CatViewInterface vi){
        this.icatView=vi;
        this.catrepo = r;
        //this.client = _client;
    }
    @Override
    public void getAllCategories() {
        catrepo.getAllCategories(this);

    }

    @Override
    public void onCatSuccessfulResponse(List<Category> categories) {
        icatView.setCategories(categories);
    }

    @Override
    public void onCatFailureResponse(String errorMessage) {
       icatView.showErrorResponse(errorMessage);
    }
}
