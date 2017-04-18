package ru.example.mvaluyskiy.gettableapp.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.example.mvaluyskiy.gettableapp.api.network.NetworkStateReceiver;
import ru.example.mvaluyskiy.gettableapp.background.TableRefreshService;
import ru.example.mvaluyskiy.gettableapp.data.repository.AppRepository;
import ru.example.mvaluyskiy.gettableapp.di.modules.AppModule;
import ru.example.mvaluyskiy.gettableapp.di.modules.DataModule;
import ru.example.mvaluyskiy.gettableapp.di.modules.NetworkModule;
import ru.example.mvaluyskiy.gettableapp.presentation.customers.CustomersListFragment;
import ru.example.mvaluyskiy.gettableapp.presentation.customers.CustomersListPresenter;
import ru.example.mvaluyskiy.gettableapp.presentation.tables.TablesFragment;
import ru.example.mvaluyskiy.gettableapp.presentation.tables.TablesPresenter;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

@Component(modules = {AppModule.class, NetworkModule.class, DataModule.class})
@Singleton
public interface AppComponent {

    void inject(NetworkStateReceiver receiver);

    void inject(CustomersListFragment customersListFragment);

    void inject(TablesFragment tablesFragment);

    void inject(AppRepository repository);
    
    void inject(TableRefreshService service);

    CustomersListPresenter newCustomerListPresenter();

    TablesPresenter newTablesPresenter();
}