package com.berstek.models;

import java.util.Comparator;

public class Frequency implements Comparable<Frequency> {

    private String word;
    private int frequency;

    //default comparator. sort descending by frequency
    public int compareTo(Frequency o) {
        return o.frequency - frequency;
    }

    //sort ascending based on frequency
    public static Comparator<Frequency> FrequencyAscComparator = new Comparator<Frequency>() {
        public int compare(Frequency o1, Frequency o2) {
            return o1.frequency - o2.frequency;
        }
    };

    public static Comparator<Frequency> WordAscComparator = new Comparator<Frequency>() {
        public int compare(Frequency o1, Frequency o2) {
            return o1.word.compareTo(o2.word);
        }
    };
}
