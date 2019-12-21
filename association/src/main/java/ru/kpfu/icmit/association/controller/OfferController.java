package ru.kpfu.icmit.association.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kpfu.icmit.association.model.soap.Envelope;

@Controller
@RequestMapping(value = "/offer")
public class OfferController {

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public void addOffer(@RequestBody Envelope envelope) {

    }
}