package ru.example.mvaluyskiy.gettableapp.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import ru.example.mvaluyskiy.gettableapp.data.dao.CustomerDao;
import ru.example.mvaluyskiy.gettableapp.data.dao.TableDao;
import ru.example.mvaluyskiy.gettableapp.data.vo.Customer;
import ru.example.mvaluyskiy.gettableapp.data.vo.Table;

/**
 * Created by m.valuyskiy on 16.04.17.
 */

public class OrmLiteDatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "gettableapp.db";
    private static final int DATABASE_VERSION = 1;

    private CustomerDao customerDao = null;
    private TableDao tableDao = null;

    public OrmLiteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Customer.class);
            TableUtils.createTable(connectionSource, Table.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVer,
                          int newVer) {
        OrmLiteUpdateHelper updateHelper = new OrmLiteUpdateHelper();
        updateHelper.loadData(this);
        try {
            TableUtils.dropTable(connectionSource, Customer.class, true);
            TableUtils.dropTable(connectionSource, Table.class, true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        updateHelper.saveData(this);
    }

    public CustomerDao getCustomerDao() throws SQLException {
        if (customerDao == null) {
            customerDao = new CustomerDao(getConnectionSource(), Customer.class);
        }

        return customerDao;
    }

    public TableDao getTableDao() throws SQLException {
        if (tableDao == null) {
            tableDao = new TableDao(getConnectionSource(), Table.class);
        }

        return tableDao;
    }

    @Override
    public void close() {
        super.close();
        customerDao = null;
        tableDao = null;
    }
}
