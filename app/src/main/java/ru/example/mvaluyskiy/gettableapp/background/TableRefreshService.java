package ru.example.mvaluyskiy.gettableapp.background;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import ru.example.mvaluyskiy.gettableapp.GetTableApplication;
import ru.example.mvaluyskiy.gettableapp.data.repository.AppRepository;
import ru.example.mvaluyskiy.gettableapp.presentation.tables.TablesActivity;

/**
 * Created by m.valuyskiy on 16.04.17.
 */

public class TableRefreshService extends Service {
    // 10 min period
    private static int PERIOD = 600000;
    private static Timer timer = new Timer();

    @Inject
    AppRepository appRepository;

    public IBinder onBind(Intent arg) {
        return null;
    }


    public void onCreate() {
        super.onCreate();
        GetTableApplication.getAppComponent().inject(this);
        startService();
    }

    private void startService() {
        timer.scheduleAtFixedRate(new updateTask(), 0, PERIOD);
    }

    private class updateTask extends TimerTask {
        public void run() {
            appRepository.clearReservations();
            sendBroadcast(new Intent(TablesActivity.BROADCAST_ACTION_UPDATE));
        }
    }
}
