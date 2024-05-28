package com.eznema.vb_test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/admin_only")
    public ResponseEntity<String> demo() {
        return ResponseEntity.ok("Hello from admin_only url");
    }

    @GetMapping("/demo")
    public ResponseEntity<String> demo2() {
        return ResponseEntity.ok("Hello from protected url");
    }

}
