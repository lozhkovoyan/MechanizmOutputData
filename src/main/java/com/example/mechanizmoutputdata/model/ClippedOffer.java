package com.example.mechanizmoutputdata.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class ClippedOffer {

    @Id
    private UUID offerId;
    private String clippedClientFIO;
}
