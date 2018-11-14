package com.sl.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = {"/test/test"})
    public String test(){
        return "hello word!";
    }
}
