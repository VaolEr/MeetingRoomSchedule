package ru.VaolEr.meetingroomschedule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class AuthController {
    @GetMapping
    public String getLoginPage(){
        return "login";
    }

//    @PostMapping("/timetable")
//    public String returnTimetable(){
//        return "index";
//    }
}
