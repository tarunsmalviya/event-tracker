package com.tarunsmalviya.tracker;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import io.realm.Realm;

public class EventListenerService extends Service {

    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() == Intent.ACTION_SCREEN_ON) {
                Log.d("Event", "SCREEN ON");
                logEvent(System.currentTimeMillis(), 1);
            } else if (intent.getAction() == Intent.ACTION_SCREEN_OFF) {
                Log.d("Event", "SCREEN OFF");
                logEvent(System.currentTimeMillis(), 2);
            } else if (intent.getAction() == Intent.ACTION_USER_PRESENT) {
                Log.d("Event", "UNLOCK");
                logEvent(System.currentTimeMillis(), 3);
            }
        }
    };

    private void logEvent(final long timestamp, final int type) {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                EventLogModel log = realm.createObject(EventLogModel.class, timestamp);
                log.setType(type);
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();

        IntentFilter intent = new IntentFilter(Intent.ACTION_SCREEN_ON);
        intent.addAction(Intent.ACTION_SCREEN_OFF);
        intent.addAction(Intent.ACTION_USER_PRESENT);
        registerReceiver(receiver, intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        unregisterReceiver(receiver);
    }
}
