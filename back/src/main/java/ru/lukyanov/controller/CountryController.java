package ru.lukyanov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lukyanov.service.ResponseClientService;


@RestController
@RequestMapping("/api")
public class CountryController {
    @Autowired
    ResponseClientService responseClientService;

    //@GetMapping("/getFreeCountryList")
    public String getResponse(){
        String responseBody = (responseClientService.getResponseBody("https://onlinesim.ru/api/getFreeCountryList"));
        //System.out.println(responseBody);
        return responseBody;
    }


}
