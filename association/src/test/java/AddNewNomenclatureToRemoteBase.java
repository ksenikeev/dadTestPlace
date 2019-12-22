import ru.kpfu.icmit.association.model.Nomenclature;
import ru.kpfu.icmit.association.model.soap.Body;
import ru.kpfu.icmit.association.model.soap.Envelope;
import ru.kpfu.icmit.association.model.soap.Header;
import java.util.UUID;

public class AddNewNomenclatureToRemoteBase {

    public static void main(String[] args) {

        //SOAPSender sender = new SOAPSender(SOAPSender.REMOTE_URL);
        SOAPSender sender = new SOAPSender(SOAPSender.LOCAL_URL);

        Envelope envelope = new Envelope();
        Header header = new Header();
        Body body = new Body();
        envelope.setHeader(header);
        envelope.setBody(body);

        Nomenclature nomenclature = new Nomenclature("Легковой автомобиль Lada Vesta (GFL11-50-C04)", null, null);
        nomenclature.setUid(UUID.randomUUID());

        body.setContent(nomenclature);

        Envelope response = sender.sendEnvelope(envelope, "nomenclature/add");

    }
}
