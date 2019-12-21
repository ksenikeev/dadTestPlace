package ru.kpfu.icmit.association.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.icmit.association.model.Offer;
import ru.kpfu.icmit.association.repository.OfferRepository;
import java.util.ArrayList;
import java.util.List;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;

    public List<Offer> findAll() {
        List<Offer> res = new ArrayList<>();
        offerRepository.findAll().forEach(res::add);
        return res;
    }
}
