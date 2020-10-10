package com.example.applicationb;

import android.app.Application;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {
    private Object RealmConfiguration;
    private io.realm.RealmConfiguration configuration;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration = new RealmConfiguration.Builder()
                .name("movie.db")
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(configuration);
    }
}
