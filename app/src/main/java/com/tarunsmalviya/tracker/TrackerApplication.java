package com.tarunsmalviya.tracker;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class TrackerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("event_logs.realm")
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
