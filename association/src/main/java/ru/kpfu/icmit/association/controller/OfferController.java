package ru.kpfu.icmit.association.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.icmit.association.model.Nomenclature;
import ru.kpfu.icmit.association.model.Offer;
import ru.kpfu.icmit.association.model.OfferList;
import ru.kpfu.icmit.association.model.Organization;
import ru.kpfu.icmit.association.model.soap.Body;
import ru.kpfu.icmit.association.model.soap.Envelope;
import ru.kpfu.icmit.association.model.soap.Header;
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

    @RequestMapping(value = "/getbynom", method = RequestMethod.POST)
    @ResponseBody
    public Envelope getOfferByNomenclatureUID(@RequestBody Envelope envelope) {

        List<Offer> lst = null;
        System.out.println("envelope: " + envelope);

        if (envelope != null) {
            Nomenclature nomenclature = (Nomenclature) envelope.getBody().getContent();

            lst = offerRepository.findByNomenclatureUid(nomenclature.getUid());
        }

        OfferList offerList = new OfferList(lst);

        Envelope envelopeResponse = new Envelope();
        Header header = new Header();
        Body body = new Body();
        envelopeResponse.setHeader(header);
        envelopeResponse.setBody(body);

        body.setContent(offerList);

        return envelopeResponse;
    }

}