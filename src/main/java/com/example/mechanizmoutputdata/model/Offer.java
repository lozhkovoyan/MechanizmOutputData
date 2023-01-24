package com.example.mechanizmoutputdata.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Offer {

    @Id
    private UUID offerId;
    private boolean exposable;
    private String clientFullFIO;
}
