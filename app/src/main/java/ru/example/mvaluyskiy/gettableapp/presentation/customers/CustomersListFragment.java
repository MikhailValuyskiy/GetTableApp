package ru.example.mvaluyskiy.gettableapp.presentation.customers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;
import ru.example.mvaluyskiy.gettableapp.GetTableApplication;
import ru.example.mvaluyskiy.gettableapp.R;
import ru.example.mvaluyskiy.gettableapp.base.base_fragments.BaseStateFragment;
import ru.example.mvaluyskiy.gettableapp.data.vo.Customer;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

public class CustomersListFragment extends BaseStateFragment<CustomersListPresenter> implements CustomersView {

    @Override
    public CustomersListPresenter createPresenter() {
        return GetTableApplication.getAppComponent().newCustomerListPresenter();
    }

    public static Fragment newInstance() {
        return new CustomersListFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customers_list, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onCustomersLoaded(List<Customer> customerList) {

    }
}
