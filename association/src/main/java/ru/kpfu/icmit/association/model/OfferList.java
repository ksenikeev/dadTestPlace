package ru.kpfu.icmit.association.model;

import ru.kpfu.icmit.association.model.soap.Content;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class OfferList extends Content {

    private List<Offer> offerList;

    public OfferList(List<Offer> nomenclatures) {
        offerList = nomenclatures;
    }

    public OfferList() {
    }

    @XmlElement(name = "offers")
    public List<Offer> getOfferList() {
        return offerList;
    }

    public void setOfferList(List<Offer> offerList) {
        this.offerList = offerList;
    }
}
