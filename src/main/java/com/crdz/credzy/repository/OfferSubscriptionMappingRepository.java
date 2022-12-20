package com.crdz.credzy.repository;

import com.crdz.credzy.model.OfferSubscriptionMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferSubscriptionMappingRepository
        extends JpaRepository<OfferSubscriptionMapping, Long> {
    @Query(value = "SELECT subscription_id FROM offer_subscription_mapping WHERE offer_id = ?1", nativeQuery = true)
    List<Long> getOSMByOfferId(long offerId);
}
