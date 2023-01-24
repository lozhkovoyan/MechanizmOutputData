package com.example.mechanizmoutputdata.repository;

import com.example.mechanizmoutputdata.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OfferRepository extends JpaRepository<Offer, UUID> {

    List<Offer> getOffersByExposableLimitedTo(int limit);
}
