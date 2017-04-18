package ru.example.mvaluyskiy.gettableapp.presenters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import ru.example.mvaluyskiy.gettableapp.data.mappers.TableMapper;
import ru.example.mvaluyskiy.gettableapp.data.mappers.TableMapperTest;
import ru.example.mvaluyskiy.gettableapp.data.repository.AppRepository;
import ru.example.mvaluyskiy.gettableapp.data.vo.Table;
import ru.example.mvaluyskiy.gettableapp.presentation.tables.TablesPresenter;
import ru.example.mvaluyskiy.gettableapp.presentation.tables.TablesView;
import rx.Observable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Created by m.valuyskiy on 18.04.17.
 */

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 21, manifest = Config.NONE)
public class TablesPresenterTest {

    @InjectMocks
    TablesPresenter presenter;

    @InjectMocks
    TableMapper tableMapper;

    @Mock
    TablesView view;

    @Mock
    AppRepository appRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void checkGetTables_isCalled() throws Exception {
        List<Table> tables = getDummyTables();
        Observable<List<Table>> observable = Observable.just(tables);
        when(appRepository.getTablesObservable()).thenReturn(observable);
        presenter.onStart();
        verify(appRepository, times(1)).getTablesObservable();
    }

    @Test
    public void checkSetPendingState_isCalled() throws Exception {
        List<Table> tables = getDummyTables();
        Observable<List<Table>> observable = Observable.just(tables);
        when(appRepository.getTablesObservable()).thenReturn(observable);
        presenter.onStart();
        verify(view, times(1)).setPendingState();
    }

    private List<Table> getDummyTables() {
        Gson gson = new Gson();
        List<Boolean> dtos = gson.fromJson(TableMapperTest.TABLES_JSON, new TypeToken<List<Boolean>>() {
        }.getType());

        return tableMapper.getFromDtoCollection(dtos);
    }
}

