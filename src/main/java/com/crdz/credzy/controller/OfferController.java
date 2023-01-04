package com.crdz.credzy.controller;

import com.crdz.credzy.dtos.CustomerOrderDto;
import com.crdz.credzy.dtos.OfferGrabbedDto;
import com.crdz.credzy.model.Customer;
import com.crdz.credzy.model.CustomerOrders;
import com.crdz.credzy.model.Offer;
import com.crdz.credzy.repository.CustomerOrdersRepository;
import com.crdz.credzy.repository.CustomerRepository;
import com.crdz.credzy.repository.OfferRepository;
import com.crdz.credzy.repository.OfferSubscriptionMappingRepository;
import com.crdz.credzy.repository.MerchantRepository;
import com.crdz.credzy.service.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@Component
@CrossOrigin(origins = "*")
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

    @Autowired
    MerchantRepository merchantRepository;

    ZoneId zid = ZoneId.of("Asia/Kolkata");

    @PostMapping
    @RequestMapping(path = "/grab")
    public OfferGrabbedDto redeemOffer(@RequestParam Long customerId, @RequestParam Long offerId) {
        Customer customer = customerRepository.getReferenceById(customerId);
        OfferGrabbedDto offerGrabbedDto = new OfferGrabbedDto();
        Offer offer = offerRepository.getReferenceById(offerId);
        List<Long> osMappings = osmRepository.getOSMByOfferId(offerId);
        List<Long> offerIds = customerOrdersRepository.getReferenceByCustomerId(customerId);
        if (offerIds.contains(offerId)) {
            offerGrabbedDto.setMessage("Offer already grabbed");
            return offerGrabbedDto;
        }
        if (customer.getSubscriptionValidityTill().isBefore(LocalDate.now())) {
            offerGrabbedDto.setMessage("Your subscription validity is expired, please renew your subscription");
            return offerGrabbedDto;
        }
        if (offer.getValidTill().isBefore(LocalDate.now())) {
            offerGrabbedDto.setMessage("This offer is expired.");
            return offerGrabbedDto;
        }
        if (osMappings.contains(customer.getSubscriptionId())) {
            long balance = customer.getCredzyPoints() - offer.getRedeemAmount();
            if (balance >= 0) {
                customer.setCredzyPoints(balance);
                CustomerOrders customerOrders = new CustomerOrders();
                customerOrders.setCustomerId(customerId);
                customerOrders.setOfferId(offerId);
                customerOrders.setValidTill(LocalDateTime.now().plusDays(7));
                customerOrders.setOrderState("Redeem");
                String merchantName = merchantRepository.getMerchantNameById(offer.getMerchantId());
                customerOrders.setMerchantName(merchantName);
                customerOrders.setMerchantId(offer.getMerchantId());
                customerOrders.setOrderTime(LocalDateTime.now());
                RandomString rm = new RandomString(4, ThreadLocalRandom.current());
                customerOrders.setUniqueCode(rm.nextString());
                customerOrders = customerOrdersRepository.save(customerOrders);
                customerRepository.save(customer);
                offerGrabbedDto.setUniqueCode(customerOrders.getUniqueCode());
                offerGrabbedDto.setMessage("Offer activated and added to 'My Orders'.");
                return offerGrabbedDto;
            } else {
                offerGrabbedDto.setMessage("Offer can not be redeemed, not enough credzy points");
                return offerGrabbedDto;
            }
        } else {
            offerGrabbedDto.setMessage("Offer can not be redeemed, please upgrade your subscription");
            return offerGrabbedDto;
        }
    }

    @GetMapping
    @RequestMapping(path = "/verify")
    public CustomerOrderDto verifyOrder(@RequestParam String uniqueCode, @RequestParam Long merchantId) {
        return customerOrdersRepository.findByUniqueCodeAndMerchantId(uniqueCode, merchantId);
    }

    @PostMapping
    @RequestMapping(path = "/delivered")
    public CustomerOrders updateCustomerOrder(@RequestParam Long customerOrderId) {
        CustomerOrders customerOrders = customerOrdersRepository.getReferenceById(customerOrderId);
        long merchantId = customerOrders.getMerchantId();
        long customerId = customerOrders.getCustomerId();
        if (customerOrdersRepository.getCountOfOrderInLast15H(customerId, merchantId) > 0) {
            return null;
        }
        customerOrders.setRedeemTime(LocalDateTime.now(zid));
        customerOrders.setOrderState("Availed");
        return customerOrdersRepository.save(customerOrders);
    }
}
