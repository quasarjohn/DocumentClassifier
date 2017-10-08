package com.berstek.services;

import jdk.internal.org.objectweb.asm.Handle;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

@Service
public class Utils {

    public FileInputStream multipartFileToFileInputStream(MultipartFile multipartFile)
            throws IOException {
        File convFile = new File(multipartFile.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return new FileInputStream(convFile);
    }

    public String[] readDocument(FileInputStream fileInputStream) throws IOException {

        //TODO read document
        return null;
    }

    public HashMap<String, Integer> convertToFreqTable(String[] corpora) {
        HashMap<String, Integer> freqTable = new HashMap<>();
        for (String line : corpora) {
            line = line.trim();
            /*
            make sure there is only one space between lexemes because we will use single
            space as delimiter and we don't want to have empty lexemes
             */
            while (line.contains("  "))
                line = line.replace("  ", " ");

            String[] lexemes = line.split(" ");
            for(String lexeme : lexemes) {
                if(freqTable.get(lexeme) == null)
                    freqTable.put(lexeme, 1);
                else
                    freqTable.put(lexeme, freqTable.get(lexeme) + 1);
            }
        }
        return freqTable;
    }
}
