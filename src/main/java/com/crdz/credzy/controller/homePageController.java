package com.crdz.credzy.controller;

import com.crdz.credzy.dtos.HomePageOutputDto;
import com.crdz.credzy.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "homepage")
public class homePageController {

    @Autowired
    HomePageService homePageService;

    @GetMapping
    @RequestMapping(path = "/get")
    public HomePageOutputDto getHomepage(@RequestParam String city){
        HomePageOutputDto homePageOutputDto = new HomePageOutputDto();
        homePageOutputDto = homePageService.getHomePage(city);
        return homePageOutputDto;
    }

    @GetMapping
    @RequestMapping(path = "/category")
    public HomePageOutputDto getHomePage(@RequestParam String city, @RequestParam String CatName) {
        HomePageOutputDto homePageOutputDto = new HomePageOutputDto();
        homePageOutputDto = homePageService.getHomePage(city, CatName);
        return homePageOutputDto;
    }
}
