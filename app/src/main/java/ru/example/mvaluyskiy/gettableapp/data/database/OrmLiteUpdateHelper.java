package ru.example.mvaluyskiy.gettableapp.data.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ru.example.mvaluyskiy.gettableapp.data.vo.Customer;

/**
 * Created by m.valuyskiy on 16.04.17.
 */

public class OrmLiteUpdateHelper {
    private List<Customer> allCustomers;

    void loadData(OrmLiteDatabaseHelper helper) {
        allCustomers = getCustomers(helper);
    }

    protected void saveData(OrmLiteDatabaseHelper helper) {
        saveGuests(allCustomers, helper);
    }

    private List<Customer> getCustomers(OrmLiteDatabaseHelper helper) {
        List<Customer> guests = new ArrayList<>();
        try {
            guests = helper.getCustomerDao().getCustomers();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return guests;
    }

    private void saveGuests(List<Customer> customers, OrmLiteDatabaseHelper databaseHelper) {
        try {
            databaseHelper.getCustomerDao().saveOrUpdate(customers);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}