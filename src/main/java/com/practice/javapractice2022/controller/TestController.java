package com.practice.javapractice2022.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @RequestMapping(method = RequestMethod.GET, path = "/")
    @ResponseBody
    public String test() {

        return "안녕 스프링시작!!";
    }
}
