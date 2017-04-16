package ru.example.mvaluyskiy.gettableapp.data.repository;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import ru.example.mvaluyskiy.gettableapp.GetTableApplication;
import ru.example.mvaluyskiy.gettableapp.data.database.OrmLiteDatabaseHelper;
import ru.example.mvaluyskiy.gettableapp.data.vo.Customer;
import ru.example.mvaluyskiy.gettableapp.data.vo.Table;
import rx.Observable;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

public class LocalRepository implements AppDataStore {

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

    @Override
    public Observable<List<Table>> getTables() {
        try {
            return Observable.just(ormLiteDatabaseHelper.getTableDao().getTables());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void bookTable(Table table) {
        try {
            ormLiteDatabaseHelper.getTableDao().update(table);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isCustomersEmpty() {
        try {
            return ormLiteDatabaseHelper.getCustomerDao().getCustomers().isEmpty();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean isTableListEmpty() {
        try {
            return ormLiteDatabaseHelper.getTableDao().getTables().isEmpty();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
