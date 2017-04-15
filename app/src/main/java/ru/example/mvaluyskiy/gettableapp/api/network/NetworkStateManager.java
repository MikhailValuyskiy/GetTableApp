package ru.example.mvaluyskiy.gettableapp.api.network;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

public class NetworkStateManager {
    private final Context context;
    private Set<NetworkStateSubscriber> stateSubscribers = new HashSet<>();
    private NetworkStateReceiver receiver;
    private IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    private final ConnectivityManager cm;

    public NetworkStateManager(Context context) {
        this.context = context;
        cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public void subscribe(NetworkStateSubscriber subscriber) {
        stateSubscribers.add(subscriber);
        updateState();
    }

    public void unSubscribe(NetworkStateSubscriber subscriber) {
        stateSubscribers.remove(subscriber);
        updateState();
    }

    public void notifyNetworkConnected() {
        for (NetworkStateSubscriber subscriber : stateSubscribers) {
            subscriber.onNetworkConnected();
        }
    }

    public boolean isConnected() {
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

    private void updateState() {
        if (stateSubscribers.size() > 0) {
            if (receiver == null) {
                receiver = new NetworkStateReceiver();
                context.registerReceiver(receiver, filter);
            }
        } else {
            if (receiver != null) {
                context.unregisterReceiver(receiver);
                receiver = null;
            }
        }
    }

    public interface NetworkStateSubscriber {
        void onNetworkConnected();
    }
}
