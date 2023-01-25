package com.example.mechanizmoutputdata.repository;

import com.example.mechanizmoutputdata.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OfferRepository extends JpaRepository<Offer, UUID> {

    @Query("select Offer from Offer of where of.isDefauleTransfer is null and limit (?1)")
    List<Offer> getLimitByOffersByDefauleTransferNull(int first);
}
