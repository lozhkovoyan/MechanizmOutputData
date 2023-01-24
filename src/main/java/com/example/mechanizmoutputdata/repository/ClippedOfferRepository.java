package com.example.mechanizmoutputdata.repository;

import com.example.mechanizmoutputdata.model.ClippedOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClippedOfferRepository extends JpaRepository<ClippedOffer, UUID> {
}
