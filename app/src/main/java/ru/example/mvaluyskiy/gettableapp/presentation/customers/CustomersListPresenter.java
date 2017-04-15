package ru.example.mvaluyskiy.gettableapp.presentation.customers;

import javax.inject.Inject;

import ru.example.mvaluyskiy.gettableapp.base.base_presenters.BaseStatePresenter;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

public class CustomersListPresenter extends BaseStatePresenter<CustomersView> {

    @Inject
    public CustomersListPresenter() {
    }

    @Override
    public void onRetry() {
        // TODO
    }
}
