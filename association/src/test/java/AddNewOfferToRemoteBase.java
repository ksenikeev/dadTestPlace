import ru.kpfu.icmit.association.model.Nomenclature;
import ru.kpfu.icmit.association.model.Offer;
import ru.kpfu.icmit.association.model.Organization;
import ru.kpfu.icmit.association.model.soap.Body;
import ru.kpfu.icmit.association.model.soap.Envelope;
import ru.kpfu.icmit.association.model.soap.Header;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class AddNewOfferToRemoteBase {

    public static void main(String[] args) {

        //SOAPSender sender = new SOAPSender(SOAPSender.LOCAL_URL);
        SOAPSender sender = new SOAPSender(SOAPSender.REMOTE_URL);

        Envelope envelope = new Envelope();
        Header header = new Header();
        Body body = new Body();
        envelope.setHeader(header);
        envelope.setBody(body);

        Organization organization = new Organization();
        organization.setInn("1600000002");
        organization.setKpp("1601001");

        Offer offer = new Offer();
        offer.setUid(UUID.fromString("c3f9b9a2-84f6-45c5-b4cf-8be7df2168a5"));
        offer.setOrganization(organization);

        Nomenclature nomenclature = new Nomenclature();
        nomenclature.setUid(UUID.fromString("058b8777-1bc1-4b9c-8c95-34f0f3bd2623"));

        offer.setNomenclature(nomenclature);

        try {
            offer.setDateOfPerformance(new SimpleDateFormat("dd.MM.yyyy").parse("12.01.2020"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        offer.setCountOfProduct(1000f);

        offer.setPriceOfProduct(Double.valueOf(1250));

        offer.setUnitCode("piece");

        body.setContent(offer);

        sender.sendEnvelope(envelope, "offer/add");

    }
}
