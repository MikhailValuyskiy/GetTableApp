package ru.example.mvaluyskiy.gettableapp;

import android.os.Bundle;

import ru.example.mvaluyskiy.gettableapp.base.BaseActivity;
import ru.example.mvaluyskiy.gettableapp.presentation.customers.CustomersListFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showFragment(CustomersListFragment.newInstance());
    }

}
