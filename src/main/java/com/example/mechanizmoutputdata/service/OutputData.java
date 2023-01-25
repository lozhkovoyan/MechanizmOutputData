package com.example.mechanizmoutputdata.service;

import com.example.mechanizmoutputdata.model.ClippedOffer;
import com.example.mechanizmoutputdata.model.Offer;
import com.example.mechanizmoutputdata.repository.ClippedOfferRepository;
import com.example.mechanizmoutputdata.repository.OfferRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OutputData {

    private boolean isTransferPod = false;
    @Autowired
    OfferRepository offerRepository;
    @Autowired
    ClippedOfferRepository clippedOfferRepository;
    @Mock
    SenderMessages senderMessages; //add mock

    public void transferData() {
        val offerList = offerRepository.getLimitByOffersByDefauleTransferNull(10000);
        val converted = convert(offerList);
        clippedOfferRepository.saveAll(converted);
        senderMessages.sent(offerList);
        isTransferPod = true;
    }

    private List<ClippedOffer> convert(List<Offer> offerList) {
        List<ClippedOffer> clippedOffers = new ArrayList<>();
        for (Offer offer : offerList) {
            ClippedOffer clippedOffer = new ClippedOffer(UUID.randomUUID(), prepareFIO(offer.getClientFullFIO()));
            offer.setDefauleTransfer(true);
            clippedOffers.add(clippedOffer);
        }
        return clippedOffers;
    }

    private String prepareFIO(String clientFullFIO) {
        String[] strFio = clientFullFIO.split(" ");
        clientFullFIO = strFio[0] + " " + strFio[1].charAt(0) + ". " + strFio[2].charAt(0) + ". ";
        return clientFullFIO;
    }
}
