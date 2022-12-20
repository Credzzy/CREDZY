package com.crdz.credzy.repository;

import com.crdz.credzy.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long> {
    List<Categories> getReferenceByCityId(Long cityId);
}
