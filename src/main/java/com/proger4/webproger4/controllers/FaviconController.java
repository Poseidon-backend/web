package com.proger4.webproger4.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/favicon.ico")
public class FaviconController {

    @GetMapping
    public ResponseEntity<Resource> getFavicon() {
        try {
            Resource resource = new ClassPathResource("static/favicon.ico");
            return ResponseEntity.status(HttpStatus.OK).body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}