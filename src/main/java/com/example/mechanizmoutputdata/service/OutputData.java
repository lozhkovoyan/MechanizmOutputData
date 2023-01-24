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
        val offerList = offerRepository.getOffersByExposableLimitedTo(10000);
        val converted = convert(offerList);
        clippedOfferRepository.saveAll(converted);
        senderMessages.sent(offerList);
    }

    private List<ClippedOffer> convert(List<Offer> offerList) {
        List<ClippedOffer> clippedOffers = new ArrayList<>();
        for (Offer offer : offerList) {
            ClippedOffer clippedOffer = new ClippedOffer(UUID.randomUUID(), prepareFIO(offer.getClientFullFIO()));
            offer.setExposable(true);
            clippedOffers.add(clippedOffer);
        }
        return clippedOffers;
    }

    private String prepareFIO(String clientFullFIO) {
//        В строке clientFullFIO оставить только Фамилия и инициалы ИО
        String s = clientFullFIO.toLowerCase();

        StringBuilder sb = new StringBuilder();
        sb.append(s.substring(0, clientFullFIO.indexOf(' ')));

        int i = sb.length();
        int count = 0;
        while (count < 2 && i < clientFullFIO.length()) {
            if (clientFullFIO.charAt(i) == ' ') {
                count++;
                sb.append(" ").append(clientFullFIO.toUpperCase().charAt(i + 1)).append(".");
            }
            i++;
        }
        return sb.toString();
    }
}
