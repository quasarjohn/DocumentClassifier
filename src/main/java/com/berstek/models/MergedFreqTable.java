package com.berstek.models;

import java.util.HashMap;
import java.util.logging.Handler;

public class MergedFreqTable {

    private String category;
    private HashMap<String, Integer> freqTable;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public HashMap<String, Integer> getFreqTable() {
        return freqTable;
    }

    public void setFreqTable(HashMap<String, Integer> freqTable) {
        this.freqTable = freqTable;
    }
}
