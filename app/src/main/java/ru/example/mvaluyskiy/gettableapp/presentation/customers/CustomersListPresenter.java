package ru.example.mvaluyskiy.gettableapp.presentation.customers;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import ru.example.mvaluyskiy.gettableapp.base.base_presenters.BaseStatePresenter;
import ru.example.mvaluyskiy.gettableapp.data.repository.GetTableAppRepository;
import ru.example.mvaluyskiy.gettableapp.data.vo.Customer;
import rx.Subscriber;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

public class CustomersListPresenter extends BaseStatePresenter<CustomersView> {

    @Inject
    GetTableAppRepository repository;

    @Inject
    public CustomersListPresenter() {
    }

    @Override
    public void onRetry() {
        loadCustomers();
    }

    @Override
    public void onStart() {
        super.onStart();
        loadCustomers();
    }

    public void loadCustomers() {
        getView().setPendingState();
        subscribe(repository.getCustomers(), new Subscriber<List<Customer>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                // TODO Delete logger
                Log.e("CustomerListPresenter", e.toString());
                getView().showErrorMessage(e);
            }

            @Override
            public void onNext(List<Customer> customers) {
                getView().setSuccessState();
                getView().onCustomersLoaded(customers);
            }
        });
    }
}
