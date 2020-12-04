package com.example.applicationb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class List extends AppCompatActivity {
    TextView tvnodata;
    ProgressDialog dialog;
    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<Model> DataArrayList; //kit add kan ke adapter
    ImageView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = (RecyclerView) findViewById(R.id.rvdata);
        dialog = new ProgressDialog(List.this);
        tvnodata = (TextView) findViewById(R.id.tvnodata);
        tvnodata.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        //addData();
        addDataOnline();
    }

    void addDataOnline(){
        //kasih loading
        dialog.setMessage("Sedang memproses data");
        dialog.show();
        // background process
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d("hasiljson", "onResponse: " + response.toString());
                        //jika sudah berhasil debugm lanjutkan code dibawah ini
                        DataArrayList = new ArrayList<>();
                        Model modelku;
                        try {
                            Log.d("hasiljson", "onResponse: " + response.toString());
                            JSONArray jsonArray = response.getJSONArray("results");
                            Log.d("hasiljson2", "onResponse: " + jsonArray.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                modelku = new Model();
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                modelku.setId(jsonObject.getInt("id"));
                                modelku.setOriginal_title(jsonObject.getString("original_title"));
                                modelku.setOverview(jsonObject.getString("overview"));
                                modelku.setRelease_date(jsonObject.getString("release_date"));
                                modelku.setPoster_path("https://sm.imgix.net/20/44/juvxivbar.jpg?w=640&h=800&auto=compress,format&fit=clip"+jsonObject.getString("poster_path"));
                                modelku.setAdult(jsonObject.getBoolean("adult"));
                                modelku.setVote_count(jsonObject.getInt("vote_count"));
                                DataArrayList.add(modelku);
                            }
                            //untuk handle click
                            adapter = new Adapter(DataArrayList, new Adapter.Callback() {
                                @Override
                                public void onClick(int position) {
                                    Model soccer = DataArrayList.get(position);
                                    Intent intent = new Intent(getApplicationContext(), Detail.class);
                                    intent.putExtra("id",soccer.id);
                                    intent.putExtra("judul",soccer.original_title);
                                    intent.putExtra("date",soccer.release_date);
                                    intent.putExtra("deskripsi",soccer.overview);
                                    intent.putExtra("path",soccer.poster_path);
                                    startActivity(intent);
                                    Toast.makeText(List.this, ""+position, Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void test() {

                                }
                            });
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(List.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                        }

                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.d("errorku", "onError errorCode : " + error.getErrorCode());
                        Log.d("errorku", "onError errorBody : " + error.getErrorBody());
                        Log.d("errorku", "onError errorDetail : " + error.getErrorDetail());
                    }
                });
    }

}