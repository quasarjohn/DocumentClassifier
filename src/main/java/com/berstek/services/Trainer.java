package com.berstek.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

@Service
public class Trainer {

    @Autowired
    private Utils utils;

    public boolean train(MultipartFile multipartFile) throws IOException {
        FileInputStream inputStream = utils.multipartFileToFileInputStream(multipartFile);
        String[] corpora = utils.readDocument(inputStream);

        HashMap<String, Integer> freqTable = utils.convertToFreqTable(corpora);

        return true;
    }
}
