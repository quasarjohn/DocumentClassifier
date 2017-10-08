package com.berstek.services;

import com.berstek.models.Classification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

@Service
public class Classifier {

    @Autowired
    private Utils utils;

    public Classification classify(MultipartFile multipartFile) throws IOException {

        //convert multipartfile to fileinputstream so we can read it using apache poi
        FileInputStream inputStream = utils.multipartFileToFileInputStream(multipartFile);
        String document = utils.readDocument(inputStream);

        HashMap<String, Integer> freqTable = utils.convertToFreqTable(document);

        for(String k : freqTable.keySet()) {
            System.out.println(k + " : " + freqTable.get(k));
        }

        return null;
    }
}
