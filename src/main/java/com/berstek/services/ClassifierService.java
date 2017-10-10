package com.berstek.services;

import com.berstek.models.Classification;
import com.berstek.models.Lexeme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class ClassifierService {

    @Autowired
    private Utils utils;

    @Autowired
    private FreqTableService freqTableService;

    public Classification classify(MultipartFile multipartFile) throws IOException {

        HashMap<String, Double> classifications = new HashMap<>();

        //convert multipartfile to fileinputstream so we can read it using apache poi
        FileInputStream inputStream = utils.multipartFileToFileInputStream(multipartFile);
        String document = utils.readDocument(inputStream);

        //convert the document to frequency table so we can perform calculations
        HashMap<String, Integer> docFreqTable = utils.convertToFreqTable(document);

        Iterable<String> categories = freqTableService.getCategories();

        for (String category : categories) {

            System.out.println("---------" + category + "-------------");
            double result = 1;

            for (String k : docFreqTable.keySet()) {
                //total freq of the word in the document being classified
                double nx = docFreqTable.get(k) + 1.0;

                //total freq of the word in the training data
                Integer freq = freqTableService.getFrequency(category, k);
                double n = 0;
                if (freq != null)
                    n = freq;

                //total size of the vocabulary
                Integer voc = freqTableService.getVocabularyCount();
                double v = 0;
                if (voc != null)
                    v = voc;

                if (n != 0 && v != 0) {
                    System.out.println("NX: " + nx + " N: " + n + " V: " + v);
                    double probability = nx / (n + v);
                    result += (probability * 10000);
                    System.out.println(probability);
                }
            }

            classifications.put(category, result);
        }

        double totalProbability = 0;
        for (String k : classifications.keySet()) {
            totalProbability += classifications.get(k);
        }

        //convert to percentage
        for (String k : classifications.keySet()) {
            double percentage = classifications.get(k) / totalProbability;
            classifications.put(k, percentage);

            System.out.println(k + " : " + (percentage * 100) + "%");
        }

        //TODO get ARGMAX

        return null;
    }
}
