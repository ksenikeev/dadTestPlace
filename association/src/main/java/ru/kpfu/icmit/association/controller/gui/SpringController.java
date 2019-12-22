package ru.kpfu.icmit.association.controller.gui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.icmit.association.model.*;
import ru.kpfu.icmit.association.service.*;
import java.util.List;

@Controller
@RequestMapping(value = "/gui")
public class SpringController {


    @Autowired
    private NomenclatureService nomenclatureService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private OfferService offerService;

    @Autowired
    private ContractService contractService;

    @RequestMapping(value = "/nomenclature")
    public String getNomenclatureList(@ModelAttribute("model") ModelMap model) {

        List<Nomenclature> nomenclatures = nomenclatureService.findAll();

        model.addAttribute("nomenclatures", nomenclatures);

        return "/nomenclature";
    }

    @RequestMapping(value = "/organization")
    public String getOrganizationList(@ModelAttribute("model") ModelMap model)
    {
        List<Organization> organizations = organizationService.findAll();

        model.addAttribute("organizations", organizations);

        return "/organization";
    }

    @RequestMapping(value = "/request")
    public String getRequestList(@ModelAttribute("model") ModelMap model)
    {
        List<Request> requests = requestService.findAll();

        model.addAttribute("requests", requests);

        return "/request";
    }

    @RequestMapping(value = "/offer")
    public String getOfferList(@ModelAttribute("model") ModelMap model)
    {
        List<Offer> offers = offerService.findAll();

        model.addAttribute("offers", offers);

        return "/offer";
    }

    @RequestMapping(value = "/contract")
    public String getContractList(@ModelAttribute("model") ModelMap model)
    {
        List<Contract> contracts = contractService.findAll();

        model.addAttribute("contracts", contracts);

        return "/contract";
    }
}