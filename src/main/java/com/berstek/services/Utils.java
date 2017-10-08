package com.berstek.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.TreeMap;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

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

    public void writeFileToDisk(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.close();
    }

    public String readDocument(FileInputStream fileInputStream) throws IOException {

        XWPFDocument docx = null;
        XWPFWordExtractor extractor;
        try {
            docx = new XWPFDocument(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        extractor = new XWPFWordExtractor(docx);
        try {
            extractor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return extractor.getText();
    }

    public TreeMap<String, Integer> convertToFreqTable(String document) {
        document = document.replaceAll("[^A-Za-z0-9]", " ");
        String[] corpora = document.split("\n");
        TreeMap<String, Integer> freqTable = new TreeMap<>();
        for (String line : corpora) {
            line = line.trim();

            while (line.contains("  "))
                line = line.replace("  ", " ");
            //END clean document

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
