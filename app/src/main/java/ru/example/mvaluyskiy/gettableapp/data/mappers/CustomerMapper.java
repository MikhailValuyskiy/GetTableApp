package ru.example.mvaluyskiy.gettableapp.data.mappers;

import android.support.annotation.NonNull;

import ru.example.mvaluyskiy.gettableapp.data.dto.CustomerDto;
import ru.example.mvaluyskiy.gettableapp.data.vo.Customer;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

public class CustomerMapper extends BaseMapper<Customer, CustomerDto> {

    @Override
    Customer getFromDto(@NonNull CustomerDto customerDto) {
        return new Customer.Builder().setId(customerDto.getId())
                .setFirstName(customerDto.getFirstName())
                .setLastName(customerDto.getLastName())
                .build();
    }

    @Override
    CustomerDto getFromVo(@NonNull Customer customer) {
        // TODO Implement when necessary
        throw new UnsupportedOperationException("Converting vo object Customer to CustomerDto is not supported." +
                " Did you forget to implement this?");
    }
}
