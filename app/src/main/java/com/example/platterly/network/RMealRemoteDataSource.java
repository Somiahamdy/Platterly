package com.example.platterly.network;

public interface RMealRemoteDataSource {
    public void makeNetworkCall(RMealNetworkCallBack networkCallBack);
    public void dmakeNetworkCall(RMealNetworkCallBack networkCallBack,String s);
    public void cmakeNetworkCall(RMealNetworkCallBack networkCallBack , String s); //search by cat
    public void imakeNetworkCall(RMealNetworkCallBack networkCallBack,String s); //search by ing
    public void amakeNetworkCall(RMealNetworkCallBack networkCallBack,String s); //search by country
    public void smakeNetworkCall(RMealNetworkCallBack networkCallBack,String s); //search by name
    public void CountryNetworkCall(CountryNetworkCallBack countryNetworkCallBack);
}
