package ru.example.mvaluyskiy.gettableapp.presentation.customers;

import java.util.List;

import ru.example.mvaluyskiy.gettableapp.base.base_views.BaseStateView;
import ru.example.mvaluyskiy.gettableapp.data.vo.Customer;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

public interface CustomersView extends BaseStateView {

    void onCustomersLoaded(List<Customer> customerList);

}
