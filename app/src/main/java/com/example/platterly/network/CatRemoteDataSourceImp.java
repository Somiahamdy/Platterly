package com.example.platterly.network;

import android.net.ConnectivityManager;
import android.util.Log;

import com.example.platterly.model.CatResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CatRemoteDataSourceImp implements CatRemoteDataSource{
    private static String CAT_URL="https://www.themealdb.com/api/json/v1/1/";
    NetworkService networkservice;
    private static CatRemoteDataSourceImp catRemoteDataSourceImp = null;
    private static String TAG="catremote";

    CatRemoteDataSourceImp(){
        Retrofit retrofit= new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(CAT_URL).build();
        networkservice = retrofit.create(NetworkService.class);  //to get json data

    }

    public static CatRemoteDataSourceImp getInstance(){
        if(catRemoteDataSourceImp==null){
            catRemoteDataSourceImp=new CatRemoteDataSourceImp();
        }
        return catRemoteDataSourceImp;
    }

    @Override
    public void makeNetworkCall(NetworkCallBack networkCallBack) {
        Call<CatResponse> call= networkservice.getCategories();
        call.enqueue(new Callback<CatResponse>() {
            @Override
            public void onResponse(Call<CatResponse> call, Response<CatResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG, "onResponse: successful"+response.body());
                    networkCallBack.onCatSuccessfulResponse(response.body().categories);
                }
            }

            @Override
            public void onFailure(Call<CatResponse> call, Throwable throwable) {
                Log.i(TAG, "onFailure: failed"+throwable.getMessage());
                networkCallBack.onCatFailureResponse(throwable.getMessage());
            }
        });
    }
}
