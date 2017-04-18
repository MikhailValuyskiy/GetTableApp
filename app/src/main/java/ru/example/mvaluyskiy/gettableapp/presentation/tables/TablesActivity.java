package ru.example.mvaluyskiy.gettableapp.presentation.tables;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import ru.example.mvaluyskiy.gettableapp.R;
import ru.example.mvaluyskiy.gettableapp.base.BaseActivity;

/**
 * Created by m.valuyskiy on 16.04.17.
 */

public class TablesActivity extends BaseActivity {

    public final static String BROADCAST_ACTION_UPDATE = "ru.example.mvaluyskiy.gettableapp.update";
    private BroadcastReceiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showFragment(TablesFragment.newInstance());

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Fragment tableFragment = getSupportFragmentManager().findFragmentByTag(TablesFragment.class.getName());
                if (tableFragment instanceof TablesFragment) {
                    ((TablesFragment) tableFragment).getPresenter().refreshTables();
                }
            }
        };

        IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION_UPDATE);
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
    }
}
