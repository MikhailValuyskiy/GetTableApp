package ru.example.mvaluyskiy.gettableapp.presentation.customers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.example.mvaluyskiy.gettableapp.base.base_presenters.BaseStatePresenter;
import ru.example.mvaluyskiy.gettableapp.data.repository.AppRepository;
import ru.example.mvaluyskiy.gettableapp.data.vo.Customer;
import rx.Subscriber;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

public class CustomersListPresenter extends BaseStatePresenter<CustomersView> {

    private List<Customer> customerList = new ArrayList<>();

    @Inject
    AppRepository repository;

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

    public void onSearchQuery(String query) {
        String q = query.toUpperCase();
        List<Customer> result = new ArrayList<>();
        for (Customer item : customerList) {
            if ((item.getFirstName().toUpperCase().contains(q)) || (item.getLastName().toUpperCase().contains(q))) {
                result.add(item);
            }
        }
        getView().onCustomersLoaded(result);
    }

    public void loadCustomers() {
        getView().setPendingState();
        subscribe(repository.getCustomersObservable(), new Subscriber<List<Customer>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (repository.isCustomersEmpty()) {
                    getView().setErrorState(e);
                } else {
                    getView().showErrorMessage(e);
                }
            }

            @Override
            public void onNext(List<Customer> customers) {
                customerList = customers;
                getView().setSuccessState();
                getView().onCustomersLoaded(customers);
            }
        });
    }
}
