package com.lms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LMSController {
    @GetMapping(path = "/hellworld")
    public String helloworld() {
        return "Hello World";
    }

   // @PostMapping(path = "/addbooks")
    //public void addBooks(@RequestBody ) {

    //}
}
