package ru.example.mvaluyskiy.gettableapp.base.base_presenters;

import javax.inject.Inject;

import ru.example.mvaluyskiy.gettableapp.api.network.NetworkStateManager;
import ru.example.mvaluyskiy.gettableapp.base.base_views.BaseStateView;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

public abstract class BaseStatePresenter<View extends BaseStateView> extends BasePresenter<View> implements NetworkStateManager.NetworkStateSubscriber {

    @Inject
    NetworkStateManager networkStateManager;

    public boolean isNetworkConnected() {
        return networkStateManager.isConnected();
    }

    public void onConnectionError() {
        networkStateManager.subscribe(this);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        networkStateManager.unSubscribe(this);
    }

    @Override
    public void onNetworkConnected() {
        getView().hideSnackBar();
        onRetry();
    }

    public abstract void onRetry();
}
