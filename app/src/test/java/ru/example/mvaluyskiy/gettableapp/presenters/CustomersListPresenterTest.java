package ru.example.mvaluyskiy.gettableapp.presenters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import ru.example.mvaluyskiy.gettableapp.data.mappers.CustomerMapper;
import ru.example.mvaluyskiy.gettableapp.data.mappers.Utils;
import ru.example.mvaluyskiy.gettableapp.data.repository.AppRepository;
import ru.example.mvaluyskiy.gettableapp.data.vo.Customer;
import ru.example.mvaluyskiy.gettableapp.presentation.customers.CustomersListPresenter;
import ru.example.mvaluyskiy.gettableapp.presentation.customers.CustomersView;
import rx.Observable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Created by m.valuyskiy on 18.04.17.
 */


@RunWith(RobolectricTestRunner.class)
@Config(sdk = 21, manifest = Config.NONE)
public class CustomersListPresenterTest {

    @InjectMocks
    CustomersListPresenter presenter;

    @InjectMocks
    CustomerMapper customerMapper;

    @Mock
    CustomersView view;

    @Mock
    AppRepository appRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void checkGetCustomers_isCalled() throws Exception {
        List<Customer> customers = Utils.getDummyCustomers();
        Observable<List<Customer>> observable = Observable.just(customers);
        when(appRepository.getCustomersObservable()).thenReturn(observable);
        presenter.onStart();
        verify(appRepository, times(1)).getCustomersObservable();
    }

    @Test
    public void checkSetPendingState_isCalled() throws Exception {
        List<Customer> customers = Utils.getDummyCustomers();
        Observable<List<Customer>> observable = Observable.just(customers);
        when(appRepository.getCustomersObservable()).thenReturn(observable);
        presenter.onStart();
        verify(view, times(1)).setPendingState();
    }
}
