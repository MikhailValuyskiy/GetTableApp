package ru.example.mvaluyskiy.gettableapp.data.mappers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import ru.example.mvaluyskiy.gettableapp.data.dto.CustomerDto;
import ru.example.mvaluyskiy.gettableapp.data.vo.Customer;
import ru.example.mvaluyskiy.gettableapp.data.vo.Table;

/**
 * Created by m.valuyskiy on 18.04.17.
 */

public class Utils {

    static CustomerMapper customerMapper = new CustomerMapper();
    static TableMapper tableMapper = new TableMapper();

    public static List<Customer> getDummyCustomers() {
        Gson gson = new Gson();
        List<CustomerDto> dtos = gson.fromJson(CustomerMapperTest.CUSTOMERS_LIST_JSON, new TypeToken<List<CustomerDto>>() {
        }.getType());

        return customerMapper.getFromDtoCollection(dtos);
    }

    public static List<Table> getDummyTables() {
        Gson gson = new Gson();
        List<Boolean> dtos = gson.fromJson(TableMapperTest.TABLES_JSON, new TypeToken<List<Boolean>>() {
        }.getType());

        return tableMapper.getFromDtoCollection(dtos);
    }
}
