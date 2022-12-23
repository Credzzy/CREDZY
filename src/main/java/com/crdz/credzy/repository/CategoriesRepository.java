package com.crdz.credzy.repository;

import com.crdz.credzy.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long> {
    @Query(value = "SELECT * FROM categories WHERE active = true AND city_id=?1", nativeQuery = true)
    List<Categories> getReferenceByCityId(Long cityId);

    @Query(value = "SELECT ID FROM categories WHERE cat_name=?1", nativeQuery = true)
    long getCatIdByCatName(String catName);
}
