package com.example.calculatorapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    RecyclerView historyRecycler;
    ImageView  deleteBtn;
    TextView backBtn;
    HistoryAdapter adapter;

    private boolean isInSelectionMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        backBtn = findViewById(R.id.backHistory);
        deleteBtn = findViewById(R.id.deleteHistory);
        historyRecycler = findViewById(R.id.historyRecycler);

        historyRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HistoryAdapter(this, HistoryStorage.getHistory());
        historyRecycler.setAdapter(adapter);

        backBtn.setOnClickListener(v -> {
            if (isInSelectionMode) {
                // exit selection mode
                isInSelectionMode = false;
                adapter.enableSelectionMode(false);
                Toast.makeText(this, "Selection cancelled", Toast.LENGTH_SHORT).show();
            } else {
                finish();
            }
        });

        deleteBtn.setOnClickListener(v -> {
            if (!isInSelectionMode) {
                // Enter selection mode
                isInSelectionMode = true;
                adapter.enableSelectionMode(true);
                Toast.makeText(this, "Select items to delete", Toast.LENGTH_SHORT).show();
            } else {
                // Perform delete
                List<HistoryItem> toRemove = new ArrayList<>();
                for (Integer pos : adapter.getSelectedPositions()) {
                    toRemove.add(HistoryStorage.getHistory().get(pos));
                }
                HistoryStorage.getHistory().removeAll(toRemove);

                isInSelectionMode = false;
                adapter.enableSelectionMode(false);
                adapter.notifyDataSetChanged();
            }
        });

    }
}
