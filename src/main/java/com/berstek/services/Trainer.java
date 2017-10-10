package com.berstek.services;

import com.berstek.models.MergedFreqTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class Trainer {

    @Autowired
    private Utils utils;

    @Autowired
    private UnzipService unzipService;

    @Autowired
    FreqTableService freqTableService;

    public boolean train(MultipartFile multipartFile) throws IOException {

        System.out.println("TRAINING STARTED");
        ArrayList<MergedFreqTable> mergedFreqTables = new ArrayList<>();

        utils.writeFileToDisk(multipartFile);

        String training_path = "E://training_documents";

        unzipService.unzip(multipartFile.getOriginalFilename(),
                training_path);

        File training_documents = new File(training_path);
        for (File category : training_documents.listFiles()) {

            ArrayList<HashMap<String, Integer>> freqTables = new ArrayList<>();

            for (File document : category.listFiles()) {

                String corpora = utils.readDocument(new FileInputStream(document));
                HashMap<String, Integer> freqTable = utils.convertToFreqTable(corpora);

                freqTables.add(freqTable);
            }

            HashMap<String, Integer> mergedFreqTableMap = utils.mergeFreqTables(freqTables);

            MergedFreqTable mergedFreqTable = new MergedFreqTable();
            mergedFreqTable.setCategory(category.getName());
            mergedFreqTable.setFreqTable(mergedFreqTableMap);
            mergedFreqTables.add(mergedFreqTable);
        }

        for (MergedFreqTable freqTable : mergedFreqTables) {
            System.out.println("###############################################################");
            System.out.println(freqTable.getCategory());

            HashMap<String, Integer> map = freqTable.getFreqTable();
            for (String k : map.keySet()) {
                System.out.println(k + " : " + map.get(k));
                freqTableService.saveFreqTable(freqTable.getCategory(), k, map.get(k));
            }
        }

        //TODO dimensionality reduction

        return true;
    }
}
