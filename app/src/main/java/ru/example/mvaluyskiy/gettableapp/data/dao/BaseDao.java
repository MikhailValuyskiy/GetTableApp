package ru.example.mvaluyskiy.gettableapp.data.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by m.valuyskiy on 16.04.17.
 */

public abstract class BaseDao<T, ID> extends BaseDaoImpl<T, ID> {

    public BaseDao(ConnectionSource connectionSource,
                   Class<T> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public void saveOrUpdate(T entity) throws SQLException {
        createOrUpdate(entity);
    }

    public void saveOrUpdate(List<T> entityList) throws SQLException {
        for (T entity : entityList) {
            saveOrUpdate(entity);
        }
    }

    public void saveIfNotExist(List<T> entityList) throws SQLException {
        for (T entity : entityList) {
            createIfNotExists(entity);
        }
    }

    public List<T> getAll() throws SQLException {
        return queryForAll();
    }
}
