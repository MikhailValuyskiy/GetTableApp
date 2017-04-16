package ru.example.mvaluyskiy.gettableapp.presentation.tables;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.example.mvaluyskiy.gettableapp.GetTableApplication;
import ru.example.mvaluyskiy.gettableapp.R;
import ru.example.mvaluyskiy.gettableapp.base.base_fragments.BaseStateFragment;
import ru.example.mvaluyskiy.gettableapp.data.vo.Table;

/**
 * Created by m.valuyskiy on 16.04.17.
 */

public class TablesFragment extends BaseStateFragment<TablesPresenter> implements TablesView {

    private TablesAdapter tablesAdapter;

    @BindView(R.id.content)
    RecyclerView tablesRecyclerView;

    public TablesFragment() {
    }

    public static TablesFragment newInstance() {
        return new TablesFragment();
    }

    @Override
    public TablesPresenter createPresenter() {
        return GetTableApplication.getAppComponent().newTablesPresenter();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GetTableApplication.getAppComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tables, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        List<Table> tablesList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Table t = new Table();
            tablesList.add(t);
        }

        tablesAdapter = new TablesAdapter(tablesList, new TablesAdapter.TableClickListener() {
            @Override
            public void onTableClicked(Table table) {
                bookTable(table);
            }
        });

        GridLayoutManager gridLayoutManger = new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false);
        tablesRecyclerView.setLayoutManager(gridLayoutManger);
        tablesRecyclerView.setAdapter(tablesAdapter);


        return rootView;
    }

    @Override
    public void bookTable(Table table) {
        getPresenter().bookTable(table);
    }

    @Override
    public void onTablesLoaded(List<Table> tables) {
        tablesAdapter.setTables(tables);
    }
}
