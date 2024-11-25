package com.example.mipt_5;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText currencyFilter;
    private ListView currencyListView;
    private ArrayAdapter<String> adapter;
    private List<String> currencyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currencyFilter = findViewById(R.id.currencyFilter);
        currencyListView = findViewById(R.id.currencyListView);
        currencyList = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, currencyList);
        currencyListView.setAdapter(adapter);

        new DataLoader(this).execute("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
    }

    public void filterCurrencies(View view) {
        String filterText = currencyFilter.getText().toString().toUpperCase();
        adapter.getFilter().filter(filterText);
    }

    public void updateListView(List<String> data) {
        currencyList.clear();
        currencyList.addAll(data);
        adapter.notifyDataSetChanged();
    }
}