package com.crdz.credzy.repository;

import com.crdz.credzy.model.Merchants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantsRepository extends JpaRepository<Merchants, Long> {
    List<Merchants> findAllByCityIdAndCategoryId(long city, long catId);

    Merchants getReferenceByMerchantId(String merchantId);

    List<Merchants> findAllByCityId(Long city);
}
