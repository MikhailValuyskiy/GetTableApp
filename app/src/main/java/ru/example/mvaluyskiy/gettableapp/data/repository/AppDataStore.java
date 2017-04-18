package ru.example.mvaluyskiy.gettableapp.data.repository;

import java.util.List;

import ru.example.mvaluyskiy.gettableapp.data.vo.Customer;
import ru.example.mvaluyskiy.gettableapp.data.vo.Table;
import rx.Observable;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

public interface AppDataStore {

    Observable<List<Customer>> getCustomersObservable();

    Observable<List<Table>> getTablesObservable();

    void bookTable(Table table);

    void clearReservations();
}
