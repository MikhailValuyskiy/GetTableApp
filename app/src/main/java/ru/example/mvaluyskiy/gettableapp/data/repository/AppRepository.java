package ru.example.mvaluyskiy.gettableapp.data.repository;

import java.util.List;

import javax.inject.Inject;

import ru.example.mvaluyskiy.gettableapp.GetTableApplication;
import ru.example.mvaluyskiy.gettableapp.data.vo.Customer;
import ru.example.mvaluyskiy.gettableapp.data.vo.Table;
import rx.Observable;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

public class AppRepository implements AppDataStore {

    @Inject
    LocalRepository localRepository;

    @Inject
    RemoteRepository remoteRepository;

    public AppRepository() {
        GetTableApplication.getAppComponent().inject(this);
    }

    @Override
    public Observable<List<Customer>> getCustomersObservable() {
        return localRepository.getCustomersObservable().concatWith(remoteRepository.getCustomersObservable());
    }

    @Override
    public Observable<List<Table>> getTablesObservable() {
        return localRepository.getTablesObservable().concatWith(remoteRepository.getTablesObservable());
    }

    @Override
    public void bookTable(Table table) {
        localRepository.bookTable(table);
    }

    public boolean isCustomersEmpty() {
        return localRepository.isCustomersEmpty();
    }

    public boolean isTableListEmpty() {
        return localRepository.isTableListEmpty();
    }

    public Observable<List<Table>> getCachedTables() {
        return localRepository.getTablesObservable();
    }

    @Override
    public void clearReservations() {
        localRepository.clearReservations();
    }
}
