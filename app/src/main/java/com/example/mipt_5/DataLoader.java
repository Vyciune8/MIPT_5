package com.example.mipt_5;
import android.os.AsyncTask;
import android.util.Log;

import com.example.mipt_5.MainActivity;
import com.example.mipt_5.Parser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DataLoader extends AsyncTask<String, Void, List<String>> {
    private static final String TAG = "DataLoader";
    private MainActivity activity;

    public DataLoader(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected List<String> doInBackground(String... urls) {
        List<String> result = new ArrayList<>();
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            try (InputStream inputStream = connection.getInputStream()) {
                result = Parser.parseXML(inputStream);
            }
        } catch (IOException e) {
            Log.e(TAG, "Error loading data: " + e.getMessage(), e);
        }
        return result; 
    }

    @Override
    protected void onPostExecute(List<String> result) {
        activity.updateListView(result);
    }
}