package com.crdz.credzy.repository;

import com.crdz.credzy.model.Merchants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantRepository extends JpaRepository<Merchants, Long> {
    @Query(value = "SELECT * FROM merchants where city_id = ?1", nativeQuery = true)
    List<Long> getMerchantsByCityId(Long cityId);

    @Query(value = "SELECT * FROM merchants WHERE id in ?1 AND is_top_brand = true", nativeQuery = true)
    List<Merchants> getTopBrandsByMerchantIdsAndActive(List<Long> merchantIds);

}
