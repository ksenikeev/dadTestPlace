import ru.kpfu.icmit.association.model.Nomenclature;
import ru.kpfu.icmit.association.model.RequestList;
import ru.kpfu.icmit.association.model.soap.Body;
import ru.kpfu.icmit.association.model.soap.Envelope;
import ru.kpfu.icmit.association.model.soap.Header;
import java.util.UUID;

/**
 * Получить Запросы товара с указанным UID
 * передаем в блоке номенклатура конверта только код UID
 */
public class GetRequestsByNomenclatureUid {

    public static void main(String[] args) {

        //SOAPSender sender = new SOAPSender(SOAPSender.LOCAL_URL);
        SOAPSender sender = new SOAPSender(SOAPSender.REMOTE_URL);

        Envelope envelope = new Envelope();
        Header header = new Header();
        Body body = new Body();
        envelope.setHeader(header);
        envelope.setBody(body);

        Nomenclature nomenclature = new Nomenclature();
        // Здесь указываем код продукта
        nomenclature.setUid(UUID.fromString("058b8777-1bc1-4b9c-8c95-34f0f3bd2623"));

        body.setContent(nomenclature);

        Envelope response = sender.sendEnvelope(envelope, "request/getbynom");

        ((RequestList)response.getBody().getContent()).getRequestList().forEach(System.out::println);
    }
}
