package ru.example.mvaluyskiy.gettableapp.data.repository;

import java.util.List;

import javax.inject.Inject;

import ru.example.mvaluyskiy.gettableapp.GetTableApplication;
import ru.example.mvaluyskiy.gettableapp.data.vo.Customer;
import rx.Observable;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

public class GetTableAppRepository implements GetTableDataStore {

    @Inject
    LocalRepository localRepository;

    @Inject
    RemoteRepository remoteRepository;

    public GetTableAppRepository() {
        GetTableApplication.getAppComponent().inject(this);
    }

    @Override
    public Observable<List<Customer>> getCustomers() {
        return localRepository.getCustomers().concatWith(remoteRepository.getCustomers());
    }
}
