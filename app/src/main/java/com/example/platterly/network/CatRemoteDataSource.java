package com.example.platterly.network;

import android.net.ConnectivityManager;

public interface CatRemoteDataSource {
    public void makeNetworkCall(NetworkCallBack networkCallback);
}
