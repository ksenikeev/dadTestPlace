package ru.kpfu.icmit.association.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.icmit.association.model.Nomenclature;
import ru.kpfu.icmit.association.model.NomenclatureList;
import ru.kpfu.icmit.association.model.Organization;
import ru.kpfu.icmit.association.model.soap.Body;
import ru.kpfu.icmit.association.model.soap.Envelope;
import ru.kpfu.icmit.association.repository.OrganizationRepository;
import ru.kpfu.icmit.association.service.NomenclatureService;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/nomenclature")
public class NomenclatureController {

    @Autowired
    private NomenclatureService nomenclatureService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Envelope addNomenclature(@RequestBody Envelope envelope) {

        System.out.println("envelope: " + envelope);

        if (envelope != null) {
            Nomenclature nomenclature = (Nomenclature) envelope.getBody().getContent();

            nomenclature.setCreateDate(new Date());
            nomenclature.setModifyDate(new Date());

            nomenclature = nomenclatureService.save(nomenclature);

            nomenclature.setId(nomenclature.getId());

            envelope.getBody().setContent(nomenclature);
        }
        return envelope;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public Envelope getNomenclature(@RequestParam(name = "datefrom") String datefrom,
                                    HttpServletResponse response) {

        System.out.println("datefrom="+datefrom);
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SXXX").
                        parse(datefrom);
        } catch (ParseException | NullPointerException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        List<Nomenclature> nomenclatures = nomenclatureService.getNomenclature(date);

        System.out.println("nomenclatures" + nomenclatures);

        Envelope envelope = new Envelope();
        Body body = new Body();

        NomenclatureList nomenclatureList = new NomenclatureList(nomenclatures);
        body.setContent(nomenclatureList);
        envelope.setBody(body);

        return envelope;
    }
}