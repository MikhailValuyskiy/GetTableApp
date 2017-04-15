package ru.example.mvaluyskiy.gettableapp.data.repository;

import java.util.List;

import ru.example.mvaluyskiy.gettableapp.data.vo.Customer;
import rx.Observable;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

public interface GetTableDataStore {

    public Observable<List<Customer>> getCustomers();
}
