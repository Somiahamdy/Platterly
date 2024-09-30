package com.example.platterly.model;

import com.example.platterly.network.CatRemoteDataSource;
import com.example.platterly.network.NetworkCallBack;

public class CatRepositoryImp implements CatRepository {
    private CatRemoteDataSource catRemoteDataSource;
    private static CatRepositoryImp catRepositoryImp=null;

    CatRepositoryImp(CatRemoteDataSource _catRemoteDataSource){
        this.catRemoteDataSource = _catRemoteDataSource;
    }

    public static CatRepositoryImp getInstance(CatRemoteDataSource _catRemoteDataSource ){
        if(catRepositoryImp == null){
            catRepositoryImp = new CatRepositoryImp(_catRemoteDataSource);
        }
        return catRepositoryImp;
    }
    @Override
    public void getAllCategories(NetworkCallBack networkCallBack) {
         catRemoteDataSource.makeNetworkCall(networkCallBack);
    }
}
