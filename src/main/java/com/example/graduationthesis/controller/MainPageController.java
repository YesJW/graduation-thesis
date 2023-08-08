package com.example.graduationthesis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainPageController {

    @GetMapping("/mainPage")
    public ModelAndView mainPage(){
        ModelAndView mav = new ModelAndView("mainPage");

        return mav;
    }
}
