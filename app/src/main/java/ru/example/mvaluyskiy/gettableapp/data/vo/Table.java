package ru.example.mvaluyskiy.gettableapp.data.vo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.UUID;

/**
 * Created by m.valuyskiy on 16.04.17.
 */


@DatabaseTable(tableName = "table")
public class Table {
    @DatabaseField(id = true)
    private UUID id;
    @DatabaseField(columnName = "isFree")
    private boolean isFree;

    public Table() {
    }

    public Table(UUID id, boolean isFree) {
        this.id = id;
        this.isFree = isFree;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

}
