package ru.example.mvaluyskiy.gettableapp.data.vo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.UUID;

import ru.example.mvaluyskiy.gettableapp.data.dao.TableDao;

import static ru.example.mvaluyskiy.gettableapp.data.dao.TableDao.TABLES_TABLE_NAME;

/**
 * Created by m.valuyskiy on 16.04.17.
 */


@DatabaseTable(tableName = TABLES_TABLE_NAME)
public class Table {
    @DatabaseField(id = true)
    private UUID id;
    @DatabaseField(columnName = TableDao.COLUMN_STATUS)
    private boolean isFree;

    public Table() {
    }

    public Table(UUID id, boolean isFree) {
        this.id = id;
        this.isFree = isFree;
    }

    public UUID getId() {
        return id;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

}
