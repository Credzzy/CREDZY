package com.crdz.credzy.service;

import com.crdz.credzy.dtos.HomePageOutputDto;
import com.crdz.credzy.dtos.TopBrandOutputDto;
import com.crdz.credzy.model.*;
import com.crdz.credzy.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomePageService {

    @Autowired
    CategoriesRepository categoriesRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    CrazyDealsRepository crazyDealsRepository;

    @Autowired
    AdvertisementsRepository advertisementsRepository;

    public HomePageOutputDto getHomePage(String city) {
        HomePageOutputDto homepageDto = new HomePageOutputDto();
        homepageDto.setCity(city);

        Long cityId = cityRepository.getReferenceByCityName(city);
        List<Categories> categoriesList = categoriesRepository.getReferenceByCityId(cityId);
        homepageDto.setCategories(categoriesList);

        List<Long> merchantIds = merchantRepository.getMerchantsByCityId(cityId);
        List<CrazyDeals> crazyDeals = crazyDealsRepository.getReferenceByMerchantIds(merchantIds);
        homepageDto.setCrazyDeals(crazyDeals);

        List<TopBrandOutputDto> topBrandOutputDtos = new ArrayList<>();
        List<Merchants> topBrands = merchantRepository.getTopBrandsByMerchantIdsAndActive(merchantIds);
        for(Merchants topBrand : topBrands) {
            TopBrandOutputDto topBrandOutputDto = new TopBrandOutputDto();
            topBrandOutputDto.setLogo(topBrand.getTopBrandImg());
            topBrandOutputDto.setFirmName(topBrand.getFirmName());
            topBrandOutputDto.setMerchantId(topBrand.getId());
            topBrandOutputDto.setBenefitUpTo(topBrand.getBenefitUpTo());
            topBrandOutputDtos.add(topBrandOutputDto);
        }
        homepageDto.setTopBrands(topBrandOutputDtos);

        List<Advertisements> ads = advertisementsRepository.findAll();
        homepageDto.setAds(ads);


        return homepageDto;
    }

    public HomePageOutputDto getHomePage(String city, String catName) {
        HomePageOutputDto homepageDto = new HomePageOutputDto();
        homepageDto.setCity(city);

        Long cityId = cityRepository.getReferenceByCityName(city);

        homepageDto.setCategories(null);

        List<Long> merchantIds = merchantRepository.getMerchantsByCityId(cityId);
        List<CrazyDeals> crazyDeals = crazyDealsRepository.getReferenceByMerchantIds(merchantIds);
        homepageDto.setCrazyDeals(crazyDeals);

        List<TopBrandOutputDto> topBrandOutputDtos = new ArrayList<>();
        List<Merchants> topBrands = merchantRepository.getTopBrandsByMerchantIdsAndActive(merchantIds);
        for(Merchants topBrand : topBrands) {
            TopBrandOutputDto topBrandOutputDto = new TopBrandOutputDto();
            topBrandOutputDto.setLogo(topBrand.getLogo());
            topBrandOutputDto.setFirmName(topBrand.getFirmName());
            topBrandOutputDto.setMerchantId(topBrand.getId());
            topBrandOutputDto.setBenefitUpTo(topBrand.getBenefitUpTo());
            topBrandOutputDtos.add(topBrandOutputDto);
        }
        homepageDto.setTopBrands(topBrandOutputDtos);
        return homepageDto;
    }

    public List<String> getAllCityName() {
        return cityRepository.findAllCityName();
    }
}
