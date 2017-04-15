package ru.example.mvaluyskiy.gettableapp.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.example.mvaluyskiy.gettableapp.api.network.NetworkStateReceiver;
import ru.example.mvaluyskiy.gettableapp.di.modules.AppModule;
import ru.example.mvaluyskiy.gettableapp.di.modules.NetworkModule;
import ru.example.mvaluyskiy.gettableapp.presentation.customers.CustomersListPresenter;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

@Component(modules = {AppModule.class, NetworkModule.class})
@Singleton
public interface AppComponent {

    void inject(NetworkStateReceiver receiver);

    CustomersListPresenter newCustomerListPresenter();
}