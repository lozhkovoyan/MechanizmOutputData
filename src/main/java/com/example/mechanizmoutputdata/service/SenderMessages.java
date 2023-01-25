package com.example.mechanizmoutputdata.service;


import com.example.mechanizmoutputdata.model.Offer;

import java.util.List;

public interface SenderMessages {
    void sent(List<Offer> list);
}
