package ru.kpfu.icmit.association.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kpfu.icmit.association.model.Organization;
import ru.kpfu.icmit.association.model.soap.Envelope;
import ru.kpfu.icmit.association.repository.OrganizationRepository;

@Controller
@RequestMapping(value = "/organization")
public class OfferController {

    @Autowired
    private OrganizationRepository organizationRepository;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public void addOrganization(@RequestBody Envelope envelope) {

        System.out.println("envelope: " + envelope);

        if (envelope != null) {
            Organization organization = (Organization) envelope.getBody().getContent();

            Organization tmp = organizationRepository.findByInn(organization.getInn());

            if (tmp == null) {
                organizationRepository.save(organization);
            }
        }
    }
}