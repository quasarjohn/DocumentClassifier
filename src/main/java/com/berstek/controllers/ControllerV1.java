package com.berstek.controllers;

import com.berstek.models.Classification;
import com.berstek.services.ClassifierService;
import com.berstek.services.FreqTableService;
import com.berstek.services.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class ControllerV1 {

    @Autowired
    private ClassifierService classifier;

    @Autowired
    private TrainerService trainer;


    @PostMapping("/classifier")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> upload_classify(@RequestParam("file1") MultipartFile[] file1)
            throws IOException {

        MultipartFile multipartFile = file1[0];
        System.out.println("Received : " + multipartFile.getOriginalFilename());

        Classification classification = classifier.classify(multipartFile);

        return new ResponseEntity(classification, HttpStatus.OK);
    }

    @PostMapping("/trainer")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> upload_train(@RequestParam("file1") MultipartFile[] file1)
            throws IOException {
        System.out.println("TRAINING STARTED");
        MultipartFile multipartFile = file1[0];
        System.out.println("Received : " + multipartFile.getOriginalFilename());

        trainer.train(multipartFile);

        return new ResponseEntity(null, HttpStatus.OK);
    }
}
