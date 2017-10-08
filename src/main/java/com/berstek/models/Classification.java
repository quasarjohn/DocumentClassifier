package com.berstek.models;

import java.util.HashMap;

public class Classification {

    private HashMap<String, Float> results;

    public HashMap<String, Float> getResults() {
        return results;
    }

    public void setResults(HashMap<String, Float> results) {
        this.results = results;
    }
}
