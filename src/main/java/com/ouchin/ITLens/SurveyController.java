package com.ouchin.ITLens;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("survey")
public class SurveyController {

    @GetMapping("allSurvey")
    public String getAllSurveys(){
        return "hi,  this is a  test ";
    }

}
