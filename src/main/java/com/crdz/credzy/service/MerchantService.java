package com.crdz.credzy.service;

import com.crdz.credzy.dtos.CustomerOrderDto;
import com.crdz.credzy.model.Merchants;
import com.crdz.credzy.model.Offer;
import com.crdz.credzy.repository.*;
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

    @Autowired
    CustomerOrdersRepository customerOrdersRepository;

    public Merchants getMerchantByID(Long merchantId) {
        return merchantsRepository.getReferenceById(merchantId);
    }


    public List<Offer> getOfferByMerchantId(Long merchantId) {
        return offerRepository.findAllByMerchantId(merchantId);
    }

    public List<Merchants> getAllMerchants(String city) {
        Long cityId = cityRepository.getReferenceByCityName(city);
        return merchantsRepository.findAllByCityId(cityId);
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

    public List<CustomerOrderDto> getUpcomingOrder(Long merchantId) {
        return customerOrdersRepository.getUpcomingOrderByMerchant(merchantId);
    }

    public List<CustomerOrderDto> getPastOrder(Long merchantId) {
        return customerOrdersRepository.getPastOrderByMerchant(merchantId);
    }
}
