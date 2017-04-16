package ru.example.mvaluyskiy.gettableapp.data.repository;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import ru.example.mvaluyskiy.gettableapp.GetTableApplication;
import ru.example.mvaluyskiy.gettableapp.api.retrofit.ApiInterface;
import ru.example.mvaluyskiy.gettableapp.data.database.OrmLiteDatabaseHelper;
import ru.example.mvaluyskiy.gettableapp.data.dto.CustomerDto;
import ru.example.mvaluyskiy.gettableapp.data.mappers.CustomerMapper;
import ru.example.mvaluyskiy.gettableapp.data.mappers.TableMapper;
import ru.example.mvaluyskiy.gettableapp.data.vo.Customer;
import ru.example.mvaluyskiy.gettableapp.data.vo.Table;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

public class RemoteRepository implements AppDataStore {

    @Inject
    ApiInterface api;

    @Inject
    CustomerMapper mapper;

    @Inject
    TableMapper tableMapper;

    @Inject
    OrmLiteDatabaseHelper databaseHelper;

    public RemoteRepository() {
        GetTableApplication.getAppComponent().inject(this);
    }

    @Override
    public Observable<List<Customer>> getCustomers() {
        return api.getCustomers()
                .map(new Func1<List<CustomerDto>, List<Customer>>() {
                    @Override
                    public List<Customer> call(List<CustomerDto> customerDtos) {
                        List<Customer> convertedList = mapper.getFromDtoCollection(customerDtos);
                        try {
                            databaseHelper.getCustomerDao().saveOrUpdate(convertedList);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        return convertedList;
                    }
                });
    }

    @Override
    public Observable<List<Table>> getTables() {
        try {
            if (databaseHelper.getTableDao().getTables().size() > 0) {
                return Observable.just(databaseHelper.getTableDao().getTables());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return api.getTables().map(new Func1<List<Boolean>, List<Table>>() {
            @Override
            public List<Table> call(List<Boolean> booleanList) {
                List<Table> tables = tableMapper.getFromDtoCollection(booleanList);
                try {
                    databaseHelper.getTableDao().saveOrUpdate(tables);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return tables;
            }
        });
    }

    @Override
    public void bookTable(Table table) {
        // TODO Sent booking info to the api. Currently the api doesn't allow to do it.
    }

    @Override
    public void clearReservations() {
        // TODO clear reservations through api. Currently api doesn't allow todo it
    }
}
