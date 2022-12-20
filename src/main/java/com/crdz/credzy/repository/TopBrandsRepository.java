package com.crdz.credzy.repository;

import com.crdz.credzy.model.TopBrands;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopBrandsRepository extends JpaRepository<TopBrands, Long> {
}
