package ru.example.mvaluyskiy.gettableapp.data.repository;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import ru.example.mvaluyskiy.gettableapp.GetTableApplication;
import ru.example.mvaluyskiy.gettableapp.api.retrofit.ApiInterface;
import ru.example.mvaluyskiy.gettableapp.data.database.OrmLiteDatabaseHelper;
import ru.example.mvaluyskiy.gettableapp.data.dto.CustomerDto;
import ru.example.mvaluyskiy.gettableapp.data.mappers.CustomerMapper;
import ru.example.mvaluyskiy.gettableapp.data.vo.Customer;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

public class RemoteRepository implements GetTableDataStore {

    @Inject
    ApiInterface api;

    @Inject
    CustomerMapper mapper;

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
}
