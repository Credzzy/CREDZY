package com.crdz.credzy.service;

import com.crdz.credzy.model.Merchants;
import com.crdz.credzy.model.Offer;
import com.crdz.credzy.repository.CategoriesRepository;
import com.crdz.credzy.repository.CityRepository;
import com.crdz.credzy.repository.MerchantsRepository;
import com.crdz.credzy.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantService {

    @Autowired
    MerchantsRepository merchantsRepository;

    @Autowired
    OfferRepository offerRepository;

    @Autowired
    CategoriesRepository categoriesRepository;

    @Autowired
    CityRepository cityRepository;

    public Merchants getMerchantByID(Long merchantId) {
        return merchantsRepository.getReferenceById(merchantId);
    }


    public List<Offer> getofferbyMerchantId(Long merchantId) {
        return offerRepository.findAllByMerchantId(merchantId);
    }

    public List<Merchants> getAllMerchants(String city) {
        return merchantsRepository.findAllByCityId(city);
    }

    public List<Merchants> getAllMerchantsByCat(String city, long catId) {
        Long cityId = cityRepository.getReferenceByCityName(city);
        return merchantsRepository.findAllByCityIdAndCategoryId(cityId, catId);
    }

    public Merchants merchantLogin(String merchantUsername, String password) {
        Merchants merchant = merchantsRepository.getReferenceByMerchantId(merchantUsername);
        if(merchant.getPassword().equals(password)) {
            return merchant;
        }
        return null;
    }
}
