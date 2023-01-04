package com.crdz.credzy.controller;

import com.crdz.credzy.dtos.CustomerOrderDto;
import com.crdz.credzy.dtos.LoginDto;
import com.crdz.credzy.dtos.MerchantOutputDto;
import com.crdz.credzy.dtos.OfferDto;
import com.crdz.credzy.model.Merchants;
import com.crdz.credzy.model.Offer;
import com.crdz.credzy.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "merchant")
public class MerchantController {

    @Autowired
    MerchantService merchantService;



    @GetMapping
    @RequestMapping(path = "/id")
    public MerchantOutputDto getMerchant(@RequestParam Long merchantId) {
        Merchants merchants = merchantService.getMerchantByID(merchantId);
        List<Offer> offers = merchantService.getOfferByMerchantId(merchantId);
        List<OfferDto> offerDtos = createOfferDto(offers);
        return createMerchantDto(merchants, offerDtos);
    }

    private List<OfferDto> createOfferDto(List<Offer> offers) {
        List<OfferDto> offerDtos = new ArrayList<>();
        for(Offer offer : offers) {
            OfferDto offerDto = new OfferDto();
            offerDto.setOfferImg(offer.getOfferImg());
            offerDto.setOfferName(offer.getOfferName());
            offerDto.setOfferId(offer.getId());
            offerDto.setValidTillDate(offer.getValidTill());
            List<String> termsAndConditions = Arrays.asList(offer.getTermsAndConditions().split(","));
            offerDto.setTermsAndConditions(termsAndConditions);
            offerDtos.add(offerDto);
        }
        return offerDtos;
    }

    private MerchantOutputDto createMerchantDto(Merchants merchants, List<OfferDto> offer) {
        MerchantOutputDto merchant = new MerchantOutputDto();
        merchant.setAddress(merchants.getAddress());
        merchant.setFacebook(merchants.getFacebook());
        merchant.setGmap(merchants.getGmap());
        merchant.setLogo(merchants.getLogo());
        merchant.setFirmName(merchants.getFirmName());
        merchant.setInstagram(merchants.getInstagram());
        merchant.setWhatsapp(merchants.getWhatsapp());
        merchant.setMobile(merchants.getMobile());
        merchant.setOfferList(offer);
        return merchant;
    }

    @GetMapping
    @RequestMapping(path = "/all")
    public List<Merchants> getAllMerchants(@RequestParam String city) {
        return merchantService.getAllMerchants(city);
    }

    @PostMapping
    @RequestMapping(path = "login")
    public LoginDto merchantLogin(@RequestParam String merchantUsername, @RequestParam String password) {
        LoginDto loginDto = new LoginDto();
        Merchants merchant = merchantService.merchantLogin(merchantUsername,password);
        if(merchant != null ) {
            loginDto.setMessage("Login successful");
            loginDto.setCustomerId(merchant.getId());
            loginDto.setMerchantName(merchant.getFirmName());
            return loginDto;
        }
        loginDto.setMessage("Login Failed");
        return loginDto;
    }

    @GetMapping
    @RequestMapping(path = "/upcoming")
    public List<CustomerOrderDto> upcomingOrders(@RequestParam Long merchantId) {
        return merchantService.getUpcomingOrder(merchantId);
    }
}
