package ru.example.mvaluyskiy.gettableapp.data.repository;

import java.util.List;

import ru.example.mvaluyskiy.gettableapp.api.retrofit.ApiInterface;
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

    private final ApiInterface api;
    private final CustomerMapper mapper;
    private final TableMapper tableMapper;
    private final LocalRepository localRepository;

    public RemoteRepository(ApiInterface api, CustomerMapper mapper, TableMapper tableMapper, LocalRepository localRepository) {
        this.api = api;
        this.mapper = mapper;
        this.tableMapper = tableMapper;
        this.localRepository = localRepository;
    }

    @Override
    public Observable<List<Customer>> getCustomersObservable() {
        return api.getCustomers()
                .map(new Func1<List<CustomerDto>, List<Customer>>() {
                    @Override
                    public List<Customer> call(List<CustomerDto> customerDtos) {
                        List<Customer> convertedList = mapper.getFromDtoCollection(customerDtos);
                        localRepository.saveCustomers(convertedList);
                        return convertedList;
                    }
                });
    }

    @Override
    public Observable<List<Table>> getTablesObservable() {
        List<Table> tables = localRepository.getTables();
        if (tables.size() > 0) {
            return Observable.just(tables);
        }

        return api.getTables().map(new Func1<List<Boolean>, List<Table>>() {
            @Override
            public List<Table> call(List<Boolean> booleanList) {
                List<Table> tables = tableMapper.getFromDtoCollection(booleanList);
                localRepository.saveTables(tables);
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
