package com.berstek.utils;

import com.berstek.models.Document;
import com.berstek.models.FrequencyTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Trainer {

    /*
    Document classifier using Naive Bayes Theorem
     */

    private static final String[] useless_chars = {
            ",", ".", "!"
    };
    private Document[] documents;
    private List<FrequencyTable> frequencyTables;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    public void train(Document... documents) {
        this.documents = documents;

        cleanDocuments();
        convtToFrequencyTable();
        filterConstDimens();
    }

    /*
    remove double spaces and characters that are not needed for the classification
     */
    public void cleanDocuments() {
        for (Document document : documents) {

            for (String useless_char : useless_chars) {
                document.setContent(document.getContent().replace(useless_char, " "));
            }

            //remove double spaces because I will use single space as delimiter
            while (document.getContent().contains("  ")) {
                document.setContent(document.getContent().replace(" ", ""));
            }
        }
    }

    /*
    convert each document to frequency table with a frequency object
    using word as key and frequency as the value
     */
    public void convtToFrequencyTable() {
        frequencyTables = new ArrayList<FrequencyTable>();

        for (Document document : documents) {
            FrequencyTable frequencyTable = new FrequencyTable();
            frequencyTable.setTitle(document.getTitle());

            HashMap<String, Integer> frequencyMap = new HashMap<String, Integer>();

            String[] words = document.getContent().trim().split(" ");

            for (String word : words) {
                if (frequencyMap.get(word) == null)
                    frequencyMap.put(word, 1);
                else
                    frequencyMap.put(word, frequencyMap.get(word) + 1);
            }

            frequencyTable.setFrequencyMap(frequencyMap);
            frequencyTables.add(frequencyTable);
        }
    }

    /*
    remove words that have no impact to the meaning of the documents.
    these are the words/dimensions that appear in all training corpus
     */
    public void filterConstDimens() {
        //compare every word in a frequency table to every word in other frequency tables
        //this is a very resource hog operation
        //TODO I may have to use multithreading to make this thing faster
        for (int i = 0; i < frequencyTables.size(); i++) {
            //the current focused frequency map
            HashMap<String, Integer> frequencyMap = frequencyTables.get(i).getFrequencyMap();
            List<Boolean> occurence = new ArrayList<Boolean>();

            for (int j = 0; i < frequencyTables.size(); i++) {
                if (j == i)
                    continue;
                else {
                    //the frequency map we are comparing to
                    HashMap<String, Integer> compMap = frequencyTables.get(j).getFrequencyMap();

                    for (String word : frequencyMap.keySet()) {
                        //check if word exists in every other tables

                    }
                }
            }
        }
    }

    private boolean wordExistsInAllTables(List<Boolean> occurrences) {
        for (boolean occurs : occurrences) {
            if (!occurs)
                return false;
        }
        return true;
    }

    //manual tuning of the dimensions to increase accuracy
    public void trimDimens(int max_dimens) {

    }

    public void finalizeTrainingData() {

    }
}
