//package com.example.calculatorapp;
//
//import android.content.Context;
//import android.graphics.Color;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
//
//    private Context context;
//    private List<HistoryItem> historyList;
//    private Set<Integer> selectedPositions = new HashSet<>();
//    private boolean selectionMode = false;
//
//    public HistoryAdapter(Context context, List<HistoryItem> historyList) {
//        this.context = context;
//        this.historyList = historyList;
//    }
//
//    @NonNull
//    @Override
//    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.history_item, parent, false);
//        return new HistoryViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
//        HistoryItem item = historyList.get(position);
//        holder.tvExpression.setText(item.getExpression());
//        holder.tvResult.setText(item.getResult());
//
//        // Highlight if selected
//        if (selectedPositions.contains(position)) {
//            holder.itemView.setBackgroundColor(Color.parseColor("#333333")); // selected bg
//        } else {
//            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
//        }
//
//        holder.itemView.setOnClickListener(v -> {
//            if (selectionMode) {
//                if (selectedPositions.contains(position)) {
//                    selectedPositions.remove(position);
//                } else {
//                    selectedPositions.add(position);
//                }
//                notifyItemChanged(position);
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return historyList.size();
//    }
//
//    // enable/disable selection mode
//    public void enableSelectionMode(boolean enable) {
//        selectionMode = enable;
//        if (!enable) {
//            selectedPositions.clear();
//            notifyDataSetChanged();
//        }
//    }
//
//    public boolean isSelectionMode() {
//        return selectionMode;
//    }
//
//    public Set<Integer> getSelectedPositions() {
//        return selectedPositions;
//    }




package com.example.calculatorapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private Context context;
    private List<HistoryItem> historyList;
    private Set<Integer> selectedPositions = new HashSet<>();
    private boolean selectionMode = false;

    public HistoryAdapter(Context context, List<HistoryItem> historyList) {
        this.context = context;
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_item, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        HistoryItem item = historyList.get(position);
        holder.tvExpression.setText(item.getExpression());
        holder.tvResult.setText(item.getResult());

        if (selectionMode && selectedPositions.contains(position)) {
            // selected → show white rounded border
            holder.itemView.setBackgroundResource(R.drawable.history_item_selected_bg);
        } else {
            // normal → default card background
            holder.itemView.setBackgroundResource(R.drawable.history_item_bg);
        }

        holder.itemView.setOnClickListener(v -> {
            if (selectionMode) {
                if (selectedPositions.contains(position)) {
                    selectedPositions.remove(position);
                } else {
                    selectedPositions.add(position);
                }
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public void enableSelectionMode(boolean enable) {
        selectionMode = enable;
        if (!enable) {
            selectedPositions.clear();
            notifyDataSetChanged();
        }
    }

    public boolean isSelectionMode() {
        return selectionMode;
    }

    public Set<Integer> getSelectedPositions() {
        return selectedPositions;
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {

        TextView tvExpression, tvResult;
        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvExpression = itemView.findViewById(R.id.tvExpression);
            tvResult = itemView.findViewById(R.id.tvResult);
        }
    }
}

