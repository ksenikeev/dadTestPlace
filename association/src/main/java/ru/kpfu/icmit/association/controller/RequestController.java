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
import ru.kpfu.icmit.association.repository.NomenclatureRepository;
import ru.kpfu.icmit.association.repository.OrganizationRepository;
import ru.kpfu.icmit.association.repository.RequestRepository;
import java.util.List;

/**
 * Добавление в систему запроса на товары/услуги
 */
@Controller
@RequestMapping(value = "/request")
public class RequestController {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private NomenclatureRepository nomenclatureRepository;

    @Transactional
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public void addRequest(@RequestBody Envelope envelope) {

        System.out.println("envelope: " + envelope);

        if (envelope != null) {
            Request request = (Request) envelope.getBody().getContent();

            Request tmp = requestRepository.findByUid(request.getUid());

            if (tmp == null) {
                Organization organization = organizationRepository.findByInn(request.getOrganization().getInn());
                request.setOrganization(organization);

                Nomenclature nomenclature = nomenclatureRepository.findByUid(request.getNomenclature().getUid());
                request.setNomenclature(nomenclature);

                requestRepository.save(request);
            }
        }
    }

    @RequestMapping(value = "/getbynom", method = RequestMethod.POST)
    @ResponseBody
    public Envelope getRequestByNomenclatureUID(@RequestBody Envelope envelope) {

        List<Request> lst = null;
        System.out.println("envelope: " + envelope);

        if (envelope != null) {
            Nomenclature nomenclature = (Nomenclature) envelope.getBody().getContent();

            lst = requestRepository.findByNomenclatureUid(nomenclature.getUid());
        }

        RequestList requestList = new RequestList(lst);

        Envelope envelopeResponse = new Envelope();
        Header header = new Header();
        Body body = new Body();
        envelopeResponse.setHeader(header);
        envelopeResponse.setBody(body);

        body.setContent(requestList);

        return envelopeResponse;
    }

}