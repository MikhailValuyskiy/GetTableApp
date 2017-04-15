package ru.example.mvaluyskiy.gettableapp.base.base_views;

import android.support.annotation.StringRes;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

public interface BaseStateView extends BaseView {
    void setSuccessState();

    void setEmptyState(@StringRes int stringId);

    void setErrorState(Throwable throwable);

    void showErrorMessage(Throwable e);

    void setEmptyState();

    void setPendingState();

    void hideSnackBar();
}