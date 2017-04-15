package ru.example.mvaluyskiy.gettableapp.data.dao;

import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

import ru.example.mvaluyskiy.gettableapp.data.vo.Customer;

/**
 * Created by m.valuyskiy on 16.04.17.
 */

public class CustomerDao extends BaseDao<Customer, Integer> {

    public CustomerDao(ConnectionSource connectionSource, Class<Customer> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Customer> getCustomers() throws SQLException {
        QueryBuilder<Customer, Integer> queryBuilder = queryBuilder();
        return query(queryBuilder.prepare());
    }
}

