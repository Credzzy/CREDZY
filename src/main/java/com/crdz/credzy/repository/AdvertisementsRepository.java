package com.crdz.credzy.repository;

import com.crdz.credzy.model.Advertisements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementsRepository extends JpaRepository<Advertisements, Long> {

}
