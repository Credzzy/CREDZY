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
    public HomePageOutputDto getHomepage(@RequestParam String city) {
        return homePageService.getHomePage(city);
    }

    @GetMapping
    @RequestMapping(path = "/category")
    public HomePageOutputDto getHomePage(@RequestParam String city, @RequestParam String catName) {
        return homePageService.getHomePage(city, catName);
    }

    @GetMapping
    @RequestMapping(path = "/categories")
    public List<Merchants> getHomePageByCat(@RequestParam String city, @RequestParam long catId) {
        return merchantService.getAllMerchantsByCat(city, catId);
    }


}
