package com.crdz.credzy.controller;

import com.crdz.credzy.model.Customer;
import com.crdz.credzy.model.CustomerOrders;
import com.crdz.credzy.model.Offer;
import com.crdz.credzy.repository.CustomerOrdersRepository;
import com.crdz.credzy.repository.CustomerRepository;
import com.crdz.credzy.repository.OfferRepository;
import com.crdz.credzy.repository.OfferSubscriptionMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Autowired
    CustomerOrdersRepository customerOrdersRepository;

    @PostMapping
    @RequestMapping(path = "/grab")
    public String redeemOffer(@RequestParam Long customerId, @RequestParam Long offerId){
        Customer customer = customerRepository.getReferenceById(customerId);
        Offer offer = offerRepository.getReferenceById(offerId);
        List<Long> osMappings = osmRepository.getOSMByOfferId(offerId);
        List<Long> offerIds = customerOrdersRepository.getReferenceByCustomerId(customerId);
        if(offerIds.contains(offerId)) {
            return "Offer already grabbed";
        }
        if(customer.getSubscriptionValidityTill().isBefore(LocalDate.now())) {
            return "Your subscription validity is expired, please renew your subscription";
        }
        if(offer.getValidTill().isBefore(LocalDate.now())) {
            return "This offer is expired.";
        }
        if(osMappings.contains(customer.getSubscriptionId())) {
            long balance = customer.getCredzyPoints() - offer.getRedeemAmount();
            if(balance>=0) {
                customer.setCredzyPoints(balance);
                CustomerOrders customerOrders = new CustomerOrders();
                customerOrders.setCustomerId(customerId);
                customerOrders.setOfferId(offerId);
                customerOrders.setValidTill(LocalDateTime.now().plusDays(1));
                customerOrders.setOrderState("Redeem");
                customerOrdersRepository.save(customerOrders);
                customerRepository.save(customer);
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
