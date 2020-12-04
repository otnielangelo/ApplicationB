package com.example.applicationb;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

public class DetailFavourite extends AppCompatActivity {
    Bundle extras;
    String title;
    String date;
    String deskripsi;
    String path;
    String id;

    TextView tvjudul;
    ImageView ivposter;
    TextView txtdeskripsi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_favourite);
        extras = getIntent().getExtras();
        tvjudul =  findViewById(R.id.tvjudul);
        txtdeskripsi =  findViewById(R.id.txtdeskripsi);
        ivposter=  findViewById(R.id.ivposter);
        if (extras != null) {
            title = extras.getString("judul");
            date = extras.getString("date");
            deskripsi = extras.getString("deskripsi");
            path = extras.getString("path");
            id = extras.getString("id");
            tvjudul.setText(title);
            txtdeskripsi.setText(deskripsi);
            Glide.with(DetailFavourite.this)
                    .load(path)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ivposter);
        }
        //get extra
        // data tersebut tampilkan disini, jadi kalian harus layout
    }
}
