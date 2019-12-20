package ru.kpfu.icmit.association.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.icmit.association.model.Organization;
import ru.kpfu.icmit.association.model.soap.Envelope;
import ru.kpfu.icmit.association.repository.OrganizationRepository;

@Controller
@RequestMapping(value = "/organization")
public class OrganizationController {

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