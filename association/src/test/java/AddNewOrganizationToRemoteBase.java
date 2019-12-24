import ru.kpfu.icmit.association.model.Organization;
import ru.kpfu.icmit.association.model.soap.Body;
import ru.kpfu.icmit.association.model.soap.Envelope;
import ru.kpfu.icmit.association.model.soap.Header;
import java.util.UUID;

/**
 * Отправка конверта с данными организации для добавления в центральную базу данных
 */
public class AddNewOrganizationToRemoteBase {

    public static void main(String[] args) {

        SOAPSender sender = new SOAPSender(SOAPSender.LOCAL_URL);
        //SOAPSender sender = new SOAPSender(SOAPSender.REMOTE_URL);

        Envelope envelope = new Envelope();
        Header header = new Header();
        Body body = new Body();
        envelope.setHeader(header);
        envelope.setBody(body);

        Organization organization = new Organization();
        organization.setUid(UUID.randomUUID());
        organization.setNameOfOrganization("Производитель 3");
        organization.setInn("1600000003");
        organization.setKpp("1601001");
        organization.setAdressOfOrganization("г. Казань, ул. Университетская, д. 35");
        body.setContent(organization);

        sender.sendEnvelope(envelope, "organization/add");
    }
}
