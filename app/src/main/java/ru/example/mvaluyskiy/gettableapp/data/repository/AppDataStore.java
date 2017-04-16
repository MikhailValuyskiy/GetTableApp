package ru.example.mvaluyskiy.gettableapp.data.repository;

import java.util.List;

import ru.example.mvaluyskiy.gettableapp.data.vo.Customer;
import ru.example.mvaluyskiy.gettableapp.data.vo.Table;
import rx.Observable;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

public interface AppDataStore {

    Observable<List<Customer>> getCustomers();

    Observable<List<Table>> getTables();

    void bookTable(Table table);

    void clearReservations();
}
