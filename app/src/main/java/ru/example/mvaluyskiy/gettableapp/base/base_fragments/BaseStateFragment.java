package ru.example.mvaluyskiy.gettableapp.base.base_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.example.mvaluyskiy.gettableapp.BindingException;
import ru.example.mvaluyskiy.gettableapp.R;
import ru.example.mvaluyskiy.gettableapp.base.base_presenters.BaseStatePresenter;
import ru.example.mvaluyskiy.gettableapp.base.base_views.BaseStateView;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

public abstract class BaseStateFragment<Presenter extends BaseStatePresenter> extends BaseFragment<Presenter>
        implements BaseStateView {
    private static String TAG = BaseStateFragment.class.getName();
    protected View content;
    private TextView emptyMessage;
    private TextView errorMessageOnRetry;
    private View progress;
    private ViewGroup layoutError;
    private Snackbar snackbar;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        content = view.findViewById(R.id.content);
        emptyMessage = (TextView) view.findViewById(R.id.empty);
        errorMessageOnRetry = (TextView) view.findViewById(R.id.text_error);
        progress = view.findViewById(R.id.loading);
        layoutError = (ViewGroup) view.findViewById(R.id.layout_error);
        view.findViewById(R.id.button_retry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().onRetry();
            }
        });
    }

    @Override
    public void setSuccessState() {
        onViewStateChanged(ViewState.SUCCESS);
        if (content == null) {
            Log.e(TAG, BindingException.TAG, new BindingException());
            return;
        }
        hideAllViews();
        content.setVisibility(View.VISIBLE);
    }

    @Override
    public void setEmptyState(@StringRes int stringId) {
        onViewStateChanged(ViewState.EMPTY);
        if (content == null) {
            Log.e(TAG, BindingException.TAG, new BindingException());
            return;
        }
        hideAllViews();
        if (emptyMessage != null) {
            emptyMessage.setText(stringId);
            emptyMessage.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setErrorState(Throwable throwable) {
        onViewStateChanged(ViewState.ERROR);
        if (content == null) {
            Log.e(TAG, BindingException.TAG, new BindingException());
            return;
        }
        hideAllViews();
        if (!getPresenter().isNetworkConnected()) {
            getPresenter().onConnectionError();
            showRetryView(content.getContext().getString(R.string.error_no_connection));
        } else {
            showRetryView(content.getContext().getString(R.string.error_loading));
        }
    }

    @Override
    public void showErrorMessage(Throwable throwable) {
        onViewStateChanged(ViewState.ERROR);
        if (content == null) {
            Log.e(TAG, BindingException.TAG, new BindingException());
            return;
        }
        hideAllViews();
        content.setVisibility(View.VISIBLE);
        if (!getPresenter().isNetworkConnected()) {
            getPresenter().onConnectionError();
            showSnackBar(R.string.error_no_connection);
        } else {
            showSnackBar(R.string.error_loading);
        }
    }

    @Override
    public void setEmptyState() {
        setEmptyState(R.string.empty_results);
    }

    private void showRetryView(String message) {
        errorMessageOnRetry.setVisibility(View.VISIBLE);
        errorMessageOnRetry.setText(message);
        if (layoutError != null) {
            layoutError.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setPendingState() {
        onViewStateChanged(ViewState.PENDING);
        if (content == null) {
            Log.e(TAG, BindingException.TAG, new BindingException("content == null"));
            return;
        }
        hideAllViews();
        if (getPresenter().isNetworkConnected()) {
            if (enableProgressView()) {
                progress.setVisibility(View.VISIBLE);
            }
        } else {
            showSnackBar(R.string.error_no_connection);
        }
    }

    public void onViewStateChanged(ViewState state) {

    }

    public void showSnackBar(@StringRes int message) {
        Snackbar snackbar = Snackbar.make(getView(), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private void hideAllViews() {
        content.setVisibility(View.GONE);
        if (emptyMessage != null) {
            emptyMessage.setVisibility(View.GONE);
        }
        progress.setVisibility(View.GONE);
        if (layoutError != null) {
            layoutError.setVisibility(View.GONE);
        }
        errorMessageOnRetry.setVisibility(View.GONE);
        hideSnackBar();
    }

    @Override
    public void hideSnackBar() {
        if (null != snackbar && snackbar.isShown()) {
            snackbar.dismiss();
        }
    }

    public boolean enableProgressView() {
        return true;
    }

    public enum ViewState {
        PENDING, SUCCESS, ERROR, EMPTY
    }
}

