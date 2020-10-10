package com.example.applicationb;

import android.util.Log;



import io.realm.Realm;
import io.realm.RealmResults;
public class RealmHelper {

    Realm realm;

    public RealmHelper(Realm realm) {

        this.realm = realm;
    }

    // untuk menyimpan data
    public void save(final MovieRealm movieModel) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null) {
                    Log.e("Created", "Database was created");
                    Number currentIdNum = realm.where(MovieRealm.class).max("id");
                    int nextId;
                    if (currentIdNum == null) {
                        nextId = 1;
                    } else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    movieModel.setId(nextId);
                    MovieRealm model = realm.copyToRealm(movieModel);
                } else {
                    Log.e("ppppp", "execute: Database not Exist");
                }
            }
        });
    }

    public RealmResults<MovieRealm> getAllMovie() {
        RealmResults<MovieRealm> results = realm.where(MovieRealm.class).findAll();
        return results;

    }
}