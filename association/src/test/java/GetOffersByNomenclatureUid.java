import ru.kpfu.icmit.association.model.*;
import ru.kpfu.icmit.association.model.soap.Body;
import ru.kpfu.icmit.association.model.soap.Envelope;
import ru.kpfu.icmit.association.model.soap.Header;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * Запрос предложений товара с указанным UID
 * передаем в блоке номенклатура конверта только код UID
 */
public class GetOffersByNomenclatureUid {

    public static void main(String[] args) {

        SOAPSender sender = new SOAPSender(SOAPSender.LOCAL_URL);

        Envelope envelope = new Envelope();
        Header header = new Header();
        Body body = new Body();
        envelope.setHeader(header);
        envelope.setBody(body);

        Nomenclature nomenclature = new Nomenclature();
        // Здесь указываем код продукта
        nomenclature.setUid(UUID.fromString("058b8777-1bc1-4b9c-8c95-34f0f3bd2623"));

        body.setContent(nomenclature);

        Envelope response = sender.sendEnvelope(envelope, "offer/getbynom");

        ((OfferList)response.getBody().getContent()).getOfferList().forEach(System.out::println);
    }
}
