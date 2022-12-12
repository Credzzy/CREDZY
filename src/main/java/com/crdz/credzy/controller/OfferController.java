package com.crdz.credzy.controller;

import com.crdz.credzy.model.Customer;
import com.crdz.credzy.model.Offer;
import com.crdz.credzy.repository.CustomerRepository;
import com.crdz.credzy.repository.OfferRepository;
import com.crdz.credzy.repository.OfferSubscriptionMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "offer")
public class OfferController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OfferRepository offerRepository;

    @Autowired
    OfferSubscriptionMappingRepository osmRepository;

    @PostMapping
    @RequestMapping(path = "/redeem")
    public String redeemOffer(@RequestParam Long customerId, @RequestParam Long offerId){
        Customer customer = customerRepository.getReferenceById(customerId);
        Offer offer = offerRepository.getReferenceById(offerId);
        List<Long> osMappings = osmRepository.getOSMByOfferId(offerId);
        if(osMappings.contains(customer.getSubscriptionId())) {
            long balance = customer.getCredzyPoints() - offer.getOfferAmount();
            if(balance>=0) {
                customer.setCredzyPoints(balance);
                return "Offer Redeemed and added to your account";
            }
            else {
                return "Offer can not be redeemed, not enough credzy points";
            }
        }
        else {
            return "Offer can not be redeemed, please upgrade your subscription";
        }
    }
}
