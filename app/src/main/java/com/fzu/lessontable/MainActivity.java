package com.fzu.lessontable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jacky.lession.LessionDetail;
import com.jacky.lession.LessionTable;

import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LessionTable table=new LessionTable("adad","sdd");
        //试试能不能push上
    }
}