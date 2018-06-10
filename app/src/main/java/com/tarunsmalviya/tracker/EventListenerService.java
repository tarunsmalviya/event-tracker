package com.tarunsmalviya.tracker;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class EventListenerService extends Service {

    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() == Intent.ACTION_SCREEN_ON) {
                Log.e("Event", "SCREEN ON");
            } else if (intent.getAction() == Intent.ACTION_SCREEN_OFF) {
                Log.e("Event", "SCREEN OFF");
            } else if (intent.getAction() == Intent.ACTION_USER_UNLOCKED) {
                Log.e("Event", "UNLOCK");
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();

        IntentFilter intent = new IntentFilter(Intent.ACTION_SCREEN_ON);
        intent.addAction(Intent.ACTION_SCREEN_OFF);
        intent.addAction(Intent.ACTION_USER_UNLOCKED);
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
