package ru.example.mvaluyskiy.gettableapp.data.dao;

import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

import ru.example.mvaluyskiy.gettableapp.data.vo.Table;

/**
 * Created by m.valuyskiy on 16.04.17.
 */

public class TableDao extends BaseDao<Table, Integer> {

    public TableDao(ConnectionSource connectionSource, Class<Table> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Table> getTables() throws SQLException {
        QueryBuilder<Table, Integer> queryBuilder = queryBuilder();
        return query(queryBuilder.prepare());
    }
}
