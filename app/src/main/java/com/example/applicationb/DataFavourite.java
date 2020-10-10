package com.example.applicationb;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DataFavourite extends AppCompatActivity {
    Realm realm;
    RealmHelper realmHelper;
    private RecyclerView recyclerView;
    private AdapterFavourite adapter;
    private List<MovieRealm> DataArrayList; //kit add kan ke adapter


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        recyclerView = (RecyclerView) findViewById(R.id.rvdata);
        DataArrayList = new ArrayList<>();
        realmHelper = new RealmHelper(realm);
        DataArrayList = realmHelper.getAllMovie();
        adapter = new AdapterFavourite(DataArrayList, new AdapterFavourite.Callback() {
            @Override
            public void onClick(int position) {
            }
            @Override
            public void test() {
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DataFavourite.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
