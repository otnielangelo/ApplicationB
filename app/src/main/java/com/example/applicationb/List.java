package com.example.applicationb;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import java.util.ArrayList;

public class List extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Model> StudentArrayList;

    public List(ArrayList<Model> studentArrayList) {
        StudentArrayList = studentArrayList;
    }

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_list);
        recyclerView = (RecyclerView) findViewById(R.id.rvdata);
        addData();
    }
        void addData() {
            Model data1 = new Model();
            data1.setOriginal_title("Makanan");
            data1.setAdult(false);
            data1.setOverview("Makanan ini enak sekali");
            data1.setVote_count(100);
            data1.setRelease_date("01-09-2020");

            Adapter adapter = new Adapter(StudentArrayList, new Adapter.Callback() {
                @Override
                public void onClick(int position) {
                }

                @Override
                public void test() {
                }
            });
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(List.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
                //get data online
            }


    }

