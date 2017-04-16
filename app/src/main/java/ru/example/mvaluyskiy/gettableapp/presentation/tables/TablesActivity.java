package ru.example.mvaluyskiy.gettableapp.presentation.tables;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ru.example.mvaluyskiy.gettableapp.R;
import ru.example.mvaluyskiy.gettableapp.base.BaseActivity;

/**
 * Created by m.valuyskiy on 16.04.17.
 */

public class TablesActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showFragment(TablesFragment.newInstance());
    }
}
