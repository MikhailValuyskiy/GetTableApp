package ru.example.mvaluyskiy.gettableapp.base.base_presenters;

import ru.example.mvaluyskiy.gettableapp.base.base_views.BaseView;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

public class BasePresenter<View extends BaseView> {
    public static final BasePresenter INSTANCE = new BasePresenter();
    private View view;
    private final CompositeSubscription subscribers = new CompositeSubscription();
    private Scheduler jobScheduler = Schedulers.io();
    private Scheduler uiScheduler = AndroidSchedulers.mainThread();

    public final void setView(View view) {
        if (this.view != null) {
            throw new RuntimeException();
        }
        this.view = view;
    }

    public View getView() {
        return view;
    }

    public void subscribe(Observable observable) {
        observable.subscribe();
    }


    public void onCreate() {
    }

    public void onStart() {
    }

    public void onResume() {
    }

    public void onPause() {
    }

    public void onStop() {
        if (subscribers.hasSubscriptions()) {
            unsubsribe();
        }
    }

    public <T> void subscribe(Observable<T> observable, Subscriber<T> subscriber) {
        subscribers.add(observable.subscribe(subscriber));
    }

    protected void unsubsribe() {
        subscribers.clear();
    }
}
