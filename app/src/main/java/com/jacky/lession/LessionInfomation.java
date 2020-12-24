package com.jacky.lession;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

class LessionInfomation implements Runnable {
    private final String loginUrl = "http://59.77.226.32/logincheck.asp";

    HttpURLConnection connection;
    InputStream is;
    BufferedReader reader;
    String result;

    byte[] postData;

    LessionInfomation(String user, String password) throws MalformedURLException, IOException {
        String dataString = String.format("muser=%s&&passwd=%s", URLEncoder.encode(user, "UTF-8"),
                URLEncoder.encode(password, "UTF-8"));
        postData = dataString.getBytes("UTF-8");

    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            URL url = new URL(loginUrl);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
        } catch (ProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
