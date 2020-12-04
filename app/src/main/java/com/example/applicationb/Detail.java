package com.example.applicationb;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Detail extends AppCompatActivity {
    Realm realm;
    RealmHelper realmHelper;
    SoccerRealm soccerModel;
    Bundle extras;
    String title;
    String date;
    String deskripsi;
    String poster;
    String path;
    int id;

    TextView tvjudul;
    TextView tvdesc;
    ImageView ivposter;
    Button btnbookmark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        extras = getIntent().getExtras();
        tvjudul = (TextView) findViewById(R.id.tvjudul);
        tvdesc = (TextView) findViewById(R.id.txtdeskripsi);
        ivposter = (ImageView) findViewById(R.id.ivposter);
        btnbookmark = (Button) findViewById(R.id.btnbookmark);

        if (extras != null) {
            title = extras.getString("judul");
            id = extras.getInt("id");
            date = extras.getString("date");
            deskripsi = extras.getString("deskripsi");
            poster = extras.getString("poster");
            path = extras.getString("path");
            tvjudul.setText(title);
            tvdesc.setText(deskripsi);
            Glide.with(Detail.this)
                    .load(path)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ivposter);
        }else {
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        }
            Realm.init(Detail.this);
            RealmConfiguration configuration = new RealmConfiguration.Builder().build();
            realm = Realm.getInstance(configuration);


            btnbookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    soccerModel = new SoccerRealm();
                    soccerModel.setDesc(deskripsi);
                    soccerModel.setJudul(title);
                    soccerModel.setPath(path);
                    soccerModel.setReleaseDate(date);

                    realmHelper = new RealmHelper(realm);
                    realmHelper.save(soccerModel);

                    // and get whatever type user account id is
                }
            });
        }
    }