package com.xh229050532.Asian_Game_Knowledge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;

public class Main3Activity extends AppCompatActivity {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initView();
    }

    private void initView() {
        lv = findViewById(R.id.lv);
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SharedPreferences ti = getSharedPreferences("fen", MODE_PRIVATE);
        SharedPreferences.Editor edit = ti.edit();
        String tistr = ti.getString("fen", new Gson().toJson(new FenBean()));
        FenBean fromJson = new Gson().fromJson(tistr, FenBean.class);
        FenAdapter fenAdapter = new FenAdapter(this, R.layout.item_fen, fromJson.getList());
        lv.setAdapter(fenAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}