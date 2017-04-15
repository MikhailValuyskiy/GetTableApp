package ru.example.mvaluyskiy.gettableapp.api.retrofit;

import java.util.List;

import retrofit2.http.GET;
import ru.example.mvaluyskiy.gettableapp.data.dto.CustomerDto;
import rx.Observable;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

public interface ApiInterface {

    @GET("quandoo-assessment/customer-list.json")
    Observable<List<CustomerDto>> getCustomers();
}
