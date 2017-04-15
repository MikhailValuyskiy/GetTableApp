package ru.example.mvaluyskiy.gettableapp.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.example.mvaluyskiy.gettableapp.api.network.NetworkStateManager;
import ru.example.mvaluyskiy.gettableapp.api.retrofit.ApiClient;
import ru.example.mvaluyskiy.gettableapp.api.retrofit.ApiInterface;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public NetworkStateManager provideNetworkStateManger(Context context) {
        return new NetworkStateManager(context);
    }

    @Provides
    @Singleton
    public ApiInterface provideApi() {
        return ApiClient.getClient().create(ApiInterface.class);
    }
}
