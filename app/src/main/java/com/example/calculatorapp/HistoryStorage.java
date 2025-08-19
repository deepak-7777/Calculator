package com.example.calculatorapp;

import java.util.ArrayList;
import java.util.List;

public class HistoryStorage {
    private static final List<HistoryItem> historyList = new ArrayList<>();

    public static void addHistory(String expression, String result) {
        historyList.add(0, new HistoryItem(expression, result)); // latest at top
    }

    public static List<HistoryItem> getHistory() {
        return historyList;
    }

    public static void clearHistory() {
        historyList.clear();
    }
}
