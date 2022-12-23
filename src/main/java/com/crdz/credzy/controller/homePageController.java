package com.crdz.credzy.controller;

import com.crdz.credzy.dtos.HomePageOutputDto;
import com.crdz.credzy.model.Merchants;
import com.crdz.credzy.service.HomePageService;
import com.crdz.credzy.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "homepage")
public class homePageController {

    @Autowired
    HomePageService homePageService;

    @Autowired
    MerchantService merchantService;

    @GetMapping
    @RequestMapping(path = "/get")
    public HomePageOutputDto getHomepage(@RequestParam String city){
        HomePageOutputDto homePageOutputDto = new HomePageOutputDto();
        homePageOutputDto = homePageService.getHomePage(city);
        return homePageOutputDto;
    }

    @GetMapping
    @RequestMapping(path = "/category")
    public HomePageOutputDto getHomePage(@RequestParam String city, @RequestParam String catName) {
        HomePageOutputDto homePageOutputDto = new HomePageOutputDto();
        homePageOutputDto = homePageService.getHomePage(city, catName);
        return homePageOutputDto;
    }

    @GetMapping
    @RequestMapping(path = "/categories")
    public List<Merchants> getHomePageByCat(@RequestParam String city, @RequestParam long catId) {
        return merchantService.getAllMerchantsByCat(city, catId);
    }


}
