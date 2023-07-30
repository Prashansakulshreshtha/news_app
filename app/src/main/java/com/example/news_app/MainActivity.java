package com.example.news_app;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news_app.models.newsapiresponse;
import com.example.news_app.models.newsheadlines;

import java.util.List;

public class MainActivity extends AppCompatActivity implements selectlistener , View.OnClickListener{

    RecyclerView recyclerView;
    customadapter adapter;
    ProgressDialog dialog;
    Button b1,b2,b3,b4,b5,b6,b7;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dialog = new ProgressDialog(this);
        dialog.setTitle("Fetching news articles . .");
        dialog.show();

        b1 = findViewById(R.id.btn_1);
        b1.setOnClickListener(this);
        b2 = findViewById(R.id.btn_2);
        b2.setOnClickListener(this);
        b3 = findViewById(R.id.btn_3);
        b3.setOnClickListener(this);
        b4 = findViewById(R.id.btn_4);
        b4.setOnClickListener(this);
        b5 = findViewById(R.id.btn_5);
        b5.setOnClickListener(this);
        b6 = findViewById(R.id.btn_6);
        b6.setOnClickListener(this);
        b7 = findViewById(R.id.btn_7);
        b7.setOnClickListener(this);

        requestmanager manager = new requestmanager(this);
        manager.getnewsheadlines(listener,"general",null);
    }
    private final onfetchdatalistener<newsapiresponse> listener = new onfetchdatalistener<newsapiresponse>() {
        @Override
        public void onfetchdata(List<newsheadlines> list, String message) {
            if (list.isEmpty()){
                Toast.makeText(MainActivity.this, "No data found !!", Toast.LENGTH_SHORT).show();
            }
            else {
                showNews(list);
                dialog.dismiss();
            }
        }

        @Override
        public void onerror(String message) {

            Toast.makeText(MainActivity.this, "An Error ocurred !! ", Toast.LENGTH_SHORT).show();
        }
    };

    private void showNews(List<newsheadlines> list) {
        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adapter = new customadapter(this,list, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onNewsClicked(newsheadlines headlines) {

        startActivity(new Intent(MainActivity.this,DetailsActivity.class)
                .putExtra("data", headlines));
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String category = button.getText().toString();
        dialog.setTitle("Fetching news articles of" + category);
        dialog.show();
        requestmanager manager = new requestmanager(this);
        manager.getnewsheadlines(listener,category,null);
    }
}