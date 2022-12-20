package com.crdz.credzy.repository;

import com.crdz.credzy.model.CrazyDeals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrazyDealsRepository extends JpaRepository<CrazyDeals, Long> {

    @Query(value = "SELECT * FROM crazy_deals where merchant_id in ?1", nativeQuery = true)
    List<CrazyDeals> getReferenceByMerchantIds(List<Long> merchantIds);
}
