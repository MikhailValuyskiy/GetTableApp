package ru.example.mvaluyskiy.gettableapp.presentation.tables;

import java.util.List;

import javax.inject.Inject;

import ru.example.mvaluyskiy.gettableapp.base.base_presenters.BaseStatePresenter;
import ru.example.mvaluyskiy.gettableapp.data.repository.AppRepository;
import ru.example.mvaluyskiy.gettableapp.data.vo.Table;
import rx.Subscriber;

/**
 * Created by m.valuyskiy on 16.04.17.
 */

public class TablesPresenter extends BaseStatePresenter<TablesView> {

    @Inject
    AppRepository appRepository;

    @Inject
    public TablesPresenter() {
    }

    @Override
    public void onStart() {
        super.onStart();
        loadTables();
    }

    void refreshTables() {
        getView().setPendingState();
        subscribe(appRepository.getCachedTables(), new Subscriber<List<Table>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                getView().showErrorMessage(e);
            }

            @Override
            public void onNext(List<Table> tables) {
                getView().setSuccessState();
                getView().onTablesLoaded(tables);
            }
        });
    }

    @Override
    public void onRetry() {
        loadTables();
    }

    void bookTable(Table table) {
        appRepository.bookTable(table);
    }

    private void loadTables() {
        getView().setPendingState();
        subscribe(appRepository.getTablesObservable(), new Subscriber<List<Table>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (appRepository.isTableListEmpty()) {
                    getView().setErrorState(e);
                } else {
                    getView().showErrorMessage(e);
                }
                e.printStackTrace();
            }

            @Override
            public void onNext(List<Table> tables) {
                getView().setSuccessState();
                getView().onTablesLoaded(tables);
            }
        });
    }
}
