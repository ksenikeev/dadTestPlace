package ru.kpfu.icmit.association.controller.gui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.icmit.association.model.Nomenclature;
import ru.kpfu.icmit.association.model.Organization;
import ru.kpfu.icmit.association.model.Request;
import ru.kpfu.icmit.association.service.NomenclatureService;
import ru.kpfu.icmit.association.service.OrganizationService;
import ru.kpfu.icmit.association.service.RequestService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
}