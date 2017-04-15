package ru.example.mvaluyskiy.gettableapp;

import android.app.Application;

import ru.example.mvaluyskiy.gettableapp.di.AppComponent;
import ru.example.mvaluyskiy.gettableapp.di.modules.AppModule;
import ru.example.mvaluyskiy.gettableapp.di.modules.NetworkModule;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

public class GetTableApplication extends Application {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = buildComponent();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(getApplicationContext()))
                .networkModule(new NetworkModule())
                .build();
    }
}
