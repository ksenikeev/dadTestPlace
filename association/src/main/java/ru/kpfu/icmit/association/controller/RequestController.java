package ru.kpfu.icmit.association.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kpfu.icmit.association.model.Organization;
import ru.kpfu.icmit.association.model.Request;
import ru.kpfu.icmit.association.model.soap.Envelope;
import ru.kpfu.icmit.association.repository.OrganizationRepository;
import ru.kpfu.icmit.association.repository.RequestRepository;

@Controller
@RequestMapping(value = "/request")
public class RequestController {

    @Autowired
    private RequestRepository requestRepository;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public void addRequest(@RequestBody Envelope envelope) {

        System.out.println("envelope: " + envelope);

        if (envelope != null) {
            Request request = (Request) envelope.getBody().getContent();

            Request tmp = requestRepository.findByUid(request.getUid());

            if (tmp == null) {
                requestRepository.save(request);
            }
        }
    }
}