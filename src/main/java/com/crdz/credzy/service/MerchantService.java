package com.crdz.credzy.service;

import com.crdz.credzy.model.Merchants;
import com.crdz.credzy.model.Offer;
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

    public Merchants getMerchantByID(Long merchantId) {
        return merchantsRepository.getReferenceById(merchantId);
    }


    public List<Offer> getofferbyMerchantId(Long merchantId) {
        return offerRepository.findAllByMerchantId(merchantId);
    }

    public List<Merchants> getAllMerchants() {
        return merchantsRepository.findAll();
    }
}
