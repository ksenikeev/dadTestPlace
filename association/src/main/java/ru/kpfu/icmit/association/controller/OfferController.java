package ru.kpfu.icmit.association.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.icmit.association.model.Nomenclature;
import ru.kpfu.icmit.association.model.Offer;
import ru.kpfu.icmit.association.model.Organization;
import ru.kpfu.icmit.association.model.soap.Envelope;
import ru.kpfu.icmit.association.repository.NomenclatureRepository;
import ru.kpfu.icmit.association.repository.OfferRepository;
import ru.kpfu.icmit.association.repository.OrganizationRepository;

import java.util.List;

@Controller
@RequestMapping(value = "/offer")
public class OfferController {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private NomenclatureRepository nomenclatureRepository;

    @Transactional
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public void addOffer(@RequestBody Envelope envelope) {

        System.out.println("envelope: " + envelope);

        if (envelope != null) {
            Offer offer = (Offer) envelope.getBody().getContent();

            Offer tmp = offerRepository.findByUid(offer.getUid());

            if (tmp == null) {

                Organization organization = organizationRepository.findByInn(offer.getOrganization().getInn());
                offer.setOrganization(organization);

                Nomenclature nomenclature = nomenclatureRepository.findByUid(offer.getNomenclature().getUid());
                offer.setNomenclature(nomenclature);
                offerRepository.save(offer);
            }
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public List<Offer> getOffer(@RequestBody Envelope envelope) {

        List<Offer> tmp = null;
        System.out.println("envelope: " + envelope);

        if (envelope != null) {
            Nomenclature nomenclature = (Nomenclature) envelope.getBody().getContent();

            tmp = offerRepository.findByNomenclatureUid(nomenclature.getUid());

        }
        return tmp;
    }

}