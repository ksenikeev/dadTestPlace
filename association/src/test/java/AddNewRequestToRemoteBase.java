import ru.kpfu.icmit.association.model.Nomenclature;
import ru.kpfu.icmit.association.model.Organization;
import ru.kpfu.icmit.association.model.Request;
import ru.kpfu.icmit.association.model.soap.Body;
import ru.kpfu.icmit.association.model.soap.Envelope;
import ru.kpfu.icmit.association.model.soap.Header;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class AddNewRequestToRemoteBase {

    public static void main(String[] args) {

        //SOAPSender sender = new SOAPSender(SOAPSender.LOCAL_URL);
        SOAPSender sender = new SOAPSender(SOAPSender.REMOTE_URL);

        Envelope envelope = new Envelope();
        Header header = new Header();
        Body body = new Body();
        envelope.setHeader(header);
        envelope.setBody(body);

        Organization organization = new Organization();
        organization.setInn("1600000003");
        organization.setKpp("1601001");

        Request request = new Request();
        request.setUid(UUID.fromString("69254c5f-a125-45f4-8798-7f5e23894b04"));
        request.setOrganization(organization);

        Nomenclature nomenclature = new Nomenclature();
        nomenclature.setUid(UUID.fromString("058b8777-1bc1-4b9c-8c95-34f0f3bd2623"));

        request.setNomenclature(nomenclature);

        try {
            request.setDateOfPerformance(new SimpleDateFormat("dd.MM.yyyy").parse("12.01.2020"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        request.setCountOfProduct(Float.valueOf(200));

        request.setPriceOfProduct(Float.valueOf(1270));

        request.setUnitCode("piece");

        body.setContent(request);

        sender.sendEnvelope(envelope, "request/add");
    }
}
