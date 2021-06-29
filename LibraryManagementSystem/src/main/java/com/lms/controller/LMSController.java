package com.lms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LMSController {
    @GetMapping(path = "/helloworld")
    public String Helloworld() {
        return "Hello World";
    }
}
