package ru.example.mvaluyskiy.gettableapp.presentation.customers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.example.mvaluyskiy.gettableapp.GetTableApplication;
import ru.example.mvaluyskiy.gettableapp.MainActivity;
import ru.example.mvaluyskiy.gettableapp.R;
import ru.example.mvaluyskiy.gettableapp.base.base_fragments.BaseStateFragment;
import ru.example.mvaluyskiy.gettableapp.data.vo.Customer;
import ru.example.mvaluyskiy.gettableapp.presentation.tables.TablesActivity;
import ru.example.mvaluyskiy.gettableapp.ui.DividerItemDecoration;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

public class CustomersListFragment extends BaseStateFragment<CustomersListPresenter> implements CustomersView {

    private CustomersAdapter customersAdapter;

    @BindView(R.id.content)
    RecyclerView customersRecyclerView;

    public CustomersListFragment() {
    }

    @Override
    public CustomersListPresenter createPresenter() {
        return GetTableApplication.getAppComponent().newCustomerListPresenter();
    }

    public static Fragment newInstance() {
        return new CustomersListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GetTableApplication.getAppComponent().inject(this);
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
        setHasOptionsMenu(true);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        customersRecyclerView.setLayoutManager(manager);
        customersRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), R.drawable.divider));


        customersAdapter = new CustomersAdapter(new CustomersAdapter.CustomerClickListener() {
            @Override
            public void onCustomerClicked(Customer customer) {
                startActivity(new Intent(getActivity(), TablesActivity.class));
            }
        });

        customersRecyclerView.setAdapter(customersAdapter);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = new SearchView(((MainActivity) getActivity()).getSupportActionBar().getThemedContext());
        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setActionView(item, searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getPresenter().onSearchQuery(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                getPresenter().onSearchQuery(query);
                return false;
            }
        });
    }

    @Override
    public void onCustomersLoaded(List<Customer> customerList) {
        customersAdapter.setCustomers(customerList);
    }
}
