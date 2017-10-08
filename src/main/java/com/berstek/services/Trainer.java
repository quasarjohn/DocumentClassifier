package com.berstek.services;

import com.berstek.models.Lexeme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

@Service
public class Trainer {

    @Autowired
    private Utils utils;

    @Autowired
    private UnzipService unzipService;

    public boolean train(MultipartFile multipartFile) throws IOException {

        ArrayList<Lexeme> lexemes = new ArrayList<>();
        TreeMap<String, Integer> freqTable;

        utils.writeFileToDisk(multipartFile);

        String training_path = "E://training_documents";

        unzipService.unzip(multipartFile.getOriginalFilename(),
                training_path);

        File training_documents = new File(training_path);
        for (File category : training_documents.listFiles()) {
            System.out.println("-----------" + category.getName() + "--------------");
            for (File document : category.listFiles()) {
                System.out.println(document.getName());

                String corpora = utils.readDocument(new FileInputStream(document));
                freqTable = utils.convertToFreqTable(corpora);

                for (String k : freqTable.keySet()) {
                    System.out.println(k + " : " + freqTable.get(k));

                    Lexeme lexeme = new Lexeme();
                    lexeme.setWord(k);
                    lexeme.setFrequency(freqTable.get(k));
                    lexeme.setCategory(category.getName());
                    lexeme.setDocument(document.getName());

                    lexemes.add(lexeme);
                }
            }
        }

        return true;
    }
}
