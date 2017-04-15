package ru.example.mvaluyskiy.gettableapp.base;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import ru.example.mvaluyskiy.gettableapp.R;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

public class BaseActivity extends AppCompatActivity {

    public void showFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment, fragment.getClass().getName())
                .commit();
    }
}
