package ru.example.mvaluyskiy.gettableapp.base.base_fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import butterknife.Unbinder;
import ru.example.mvaluyskiy.gettableapp.base.base_presenters.BasePresenter;
import ru.example.mvaluyskiy.gettableapp.base.base_views.BaseView;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

public abstract class BaseFragment<Presenter extends BasePresenter> extends Fragment implements BaseView {

    private static final String KEY_VIEW_STATE = "key_view_state";
    private Presenter presenter;
    protected Unbinder unbinder;

    public abstract Presenter createPresenter();

    public Presenter getPresenter() {
        return presenter;
    }

    public BaseFragment() {
        setArguments(new Bundle());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (presenter == null) {
            presenter = createPresenter();
            presenter.setView(this);
            presenter.onCreate();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onStart();
        Parcelable viewState = getArguments().getParcelable(KEY_VIEW_STATE);
        if (viewState != null) {
            onRestoreViewState(viewState);
        }
    }

    @Override
    public void onStop() {
        presenter.onStop();
        getArguments().putParcelable(KEY_VIEW_STATE, onSaveViewState());
        super.onStop();
    }

    @Override
    public void onResume() {
        presenter.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroyView();
    }

    protected void onRestoreViewState(Parcelable viewState) {

    }

    protected Parcelable onSaveViewState() {
        return null;
    }

    public String getFragmentTag() {
        return BaseFragment.class.getName();
    }
}

