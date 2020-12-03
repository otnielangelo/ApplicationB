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
    public void save(final SoccerRealm soccerModel) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null) {
                    Log.e("Created", "Database was created");
                    Number currentIdNum = realm.where(SoccerRealm.class).max("id");
                    int nextId;
                    if (currentIdNum == null) {
                        nextId = 1;
                    } else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    soccerModel.setId(nextId);
                    SoccerRealm model = realm.copyToRealm(soccerModel);
                } else {
                    Log.e("ppppp", "execute: Database not Exist");
                }
            }
        });
    }

    public RealmResults<SoccerRealm> getAllSoccer() {
        RealmResults<SoccerRealm> results = realm.where(SoccerRealm.class).findAll();
        return results;

    }

    public void delete(Integer id) {
    }
}