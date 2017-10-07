package com.berstek.controllers;

import com.berstek.models.ReturnObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
public class ControllerV1 {

    @PostMapping("/classifier")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> upload_classify(@RequestParam("file1")MultipartFile[] file1) {
        System.out.println("FILE RECEIVED");

        ReturnObject object = new ReturnObject();
        object.setStatus(HttpStatus.BAD_REQUEST);

        MultipartFile file = file1[0];
        System.out.println(file.getOriginalFilename());

        object.setStatus(HttpStatus.OK);


        return new ResponseEntity(object, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test(){
        return "Hello World";
    }
}
