package ru.kpfu.icmit.association.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kpfu.icmit.association.model.*;
import ru.kpfu.icmit.association.model.soap.Body;
import ru.kpfu.icmit.association.model.soap.Envelope;
import ru.kpfu.icmit.association.model.soap.Header;
import ru.kpfu.icmit.association.repository.ContractRepository;
import ru.kpfu.icmit.association.repository.NomenclatureRepository;
import ru.kpfu.icmit.association.repository.OfferRepository;
import ru.kpfu.icmit.association.repository.RequestRepository;
import java.util.List;

@Controller
@RequestMapping(value = "/contract")
public class ContractController {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private NomenclatureRepository nomenclatureRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Transactional
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addContract(@RequestBody Envelope envelope) {

        System.out.println("envelope: " + envelope);

        if (envelope != null) {
            Contract contract = (Contract) envelope.getBody().getContent();

            Contract tmp = contractRepository.findByUid(contract.getUid());

            if (tmp == null) {

                Request request = requestRepository.findByUid(contract.getRequest().getUid());
                contract.setRequest(request);

                Offer offer = offerRepository.findByUid(contract.getOffer().getUid());
                contract.setOffer(offer);

                Nomenclature nomenclature = nomenclatureRepository.findByUid(contract.getNomenclature().getUid());
                contract.setNomenclature(nomenclature);
                contractRepository.save(contract);
            }
        }
    }

    @RequestMapping(value = "/getbynom", method = RequestMethod.POST)
    @ResponseBody
    public Envelope getContractByNomenclatureUID(@RequestBody Envelope envelope) {

        List<Contract> lst = null;
        System.out.println("envelope: " + envelope);

        if (envelope != null) {
            Nomenclature nomenclature = (Nomenclature) envelope.getBody().getContent();

            lst = contractRepository.findByNomenclatureUid(nomenclature.getUid());
        }

        ContractList contractList = new ContractList(lst);

        Envelope envelopeResponse = new Envelope();
        Header header = new Header();
        Body body = new Body();
        envelopeResponse.setHeader(header);
        envelopeResponse.setBody(body);

        body.setContent(contractList);

        return envelopeResponse;
    }

}
