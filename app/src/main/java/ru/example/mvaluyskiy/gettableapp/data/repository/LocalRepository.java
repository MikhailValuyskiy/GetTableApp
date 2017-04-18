package ru.example.mvaluyskiy.gettableapp.data.repository;

import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.util.List;

import ru.example.mvaluyskiy.gettableapp.data.dao.TableDao;
import ru.example.mvaluyskiy.gettableapp.data.database.OrmLiteDatabaseHelper;
import ru.example.mvaluyskiy.gettableapp.data.vo.Customer;
import ru.example.mvaluyskiy.gettableapp.data.vo.Table;
import rx.Observable;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

public class LocalRepository implements AppDataStore {

    OrmLiteDatabaseHelper ormLiteDatabaseHelper;

    public LocalRepository(OrmLiteDatabaseHelper ormLiteDatabaseHelper) {
        this.ormLiteDatabaseHelper = ormLiteDatabaseHelper;
    }

    public void saveCustomers(List<Customer> list) {
        try {
            ormLiteDatabaseHelper.getCustomerDao().saveOrUpdate(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveTables(List<Table> list) {
        try {
            ormLiteDatabaseHelper.getTableDao().saveOrUpdate(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Table> getTables() {
        try {
            return ormLiteDatabaseHelper.getTableDao().getTables();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Customer> getCustomers() {
        try {
            return ormLiteDatabaseHelper.getCustomerDao().getCustomers();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Observable<List<Customer>> getCustomersObservable() {
        return Observable.just(getCustomers());
    }

    @Override
    public Observable<List<Table>> getTablesObservable() {
        return Observable.just(getTables());
    }

    @Override
    public void bookTable(Table table) {
        try {
            ormLiteDatabaseHelper.getTableDao().update(table);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isCustomersEmpty() {
        try {
            return ormLiteDatabaseHelper.getCustomerDao().getCustomers().isEmpty();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean isTableListEmpty() {
        try {
            return ormLiteDatabaseHelper.getTableDao().getTables().isEmpty();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void clearReservations() {
        try {
            UpdateBuilder<Table, Integer> tableUpdateBuilder = ormLiteDatabaseHelper.getTableDao().updateBuilder();
            tableUpdateBuilder.updateColumnValue(TableDao.COLUMN_STATUS, true);
            tableUpdateBuilder.where().eq(TableDao.COLUMN_STATUS, false);
            tableUpdateBuilder.update();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
