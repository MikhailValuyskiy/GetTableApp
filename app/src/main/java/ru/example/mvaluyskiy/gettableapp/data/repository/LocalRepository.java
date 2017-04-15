package ru.example.mvaluyskiy.gettableapp.data.repository;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import ru.example.mvaluyskiy.gettableapp.GetTableApplication;
import ru.example.mvaluyskiy.gettableapp.data.database.OrmLiteDatabaseHelper;
import ru.example.mvaluyskiy.gettableapp.data.vo.Customer;
import rx.Observable;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

public class LocalRepository implements GetTableDataStore {

    @Inject
    OrmLiteDatabaseHelper ormLiteDatabaseHelper;

    public LocalRepository() {
        GetTableApplication.getAppComponent().inject(this);
    }

    @Override
    public Observable<List<Customer>> getCustomers() {
        try {
            return Observable.just(ormLiteDatabaseHelper.getCustomerDao().getCustomers());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
