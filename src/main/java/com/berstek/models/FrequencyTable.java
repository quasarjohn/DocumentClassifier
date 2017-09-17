package com.berstek.models;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class FrequencyTable {

    private String title;
    private HashMap<String, Integer> frequencyMap;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HashMap<String, Integer> getFrequencyMap() {
        return frequencyMap;
    }

    public void setFrequencyMap(HashMap<String, Integer> frequencyMap) {
        this.frequencyMap = frequencyMap;
    }
}
