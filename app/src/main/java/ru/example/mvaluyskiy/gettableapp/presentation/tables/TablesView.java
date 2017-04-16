package ru.example.mvaluyskiy.gettableapp.presentation.tables;

import java.util.List;

import ru.example.mvaluyskiy.gettableapp.base.base_views.BaseStateView;
import ru.example.mvaluyskiy.gettableapp.data.vo.Table;

/**
 * Created by m.valuyskiy on 16.04.17.
 */

public interface TablesView extends BaseStateView {

    void bookTable(Table table);

    void onTablesLoaded(List<Table> tables);
}
