package ru.example.mvaluyskiy.gettableapp.data.mappers;

import java.util.UUID;

import javax.inject.Inject;

import ru.example.mvaluyskiy.gettableapp.data.vo.Table;

/**
 * Created by m.valuyskiy on 16.04.17.
 */

public class TableMapper extends BaseMapper<Table, Boolean> {

    @Inject
    public TableMapper() {
    }

    @Override
    Table getFromDto(Boolean aBoolean) {
        return new Table(generateRandomId(), aBoolean);
    }

    @Override
    Boolean getFromVo(Table table) {
        // TODO Implement when necessary
        throw new UnsupportedOperationException("Converting vo object Table to Boolean is not supported." +
                " Did you forget to implement this?");
    }

    private UUID generateRandomId() {
        return UUID.randomUUID();
    }
}
