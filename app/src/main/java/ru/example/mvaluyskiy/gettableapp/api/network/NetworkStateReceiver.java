package ru.example.mvaluyskiy.gettableapp.api.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import ru.example.mvaluyskiy.gettableapp.GetTableApplication;
import ru.example.mvaluyskiy.gettableapp.api.network.NetworkStateManager;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

public class NetworkStateReceiver extends BroadcastReceiver {

    @Inject
    NetworkStateManager networkStateManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        GetTableApplication.getAppComponent().inject(this);
        if (networkStateManager.isConnected()) {
            networkStateManager.notifyNetworkConnected();
        }
    }
}
