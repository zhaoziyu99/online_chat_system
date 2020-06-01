package com.example.demo.web;

import com.sun.org.apache.xpath.internal.objects.XObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class DemoController {
    @RequestMapping("/")
    public Object hello(){
        System.out.println("hello world!");
        return "hello world!";
    }

}
