package com.fzu.lessontable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            HttpURLConnection connection;
            URL url = new URL("htp");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("post");
        }catch (Exception e){

        }
    }
}