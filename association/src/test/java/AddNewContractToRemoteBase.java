import ru.kpfu.icmit.association.model.*;
import ru.kpfu.icmit.association.model.soap.Body;
import ru.kpfu.icmit.association.model.soap.Envelope;
import ru.kpfu.icmit.association.model.soap.Header;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * Добавление нового контракта в удаленную базу данных
 */
public class AddNewContractToRemoteBase {

    public static void main(String[] args) {

        //SOAPSender sender = new SOAPSender(SOAPSender.LOCAL_URL); //Запрос к локальному серверу
        SOAPSender sender = new SOAPSender(SOAPSender.REMOTE_URL);  //Запрос к удаленному серверу

        Envelope envelope = new Envelope();
        Header header = new Header();
        Body body = new Body();
        envelope.setHeader(header);
        envelope.setBody(body);

        Contract contract = new Contract();
        contract.setUid(UUID.randomUUID());

        // Указываем UID номенклатуры
        Nomenclature nomenclature = new Nomenclature();
        nomenclature.setUid(UUID.fromString("058b8777-1bc1-4b9c-8c95-34f0f3bd2623"));
        contract.setNomenclature(nomenclature);

        // Указываем UID предложения
        Offer offer = new Offer();
        offer.setUid(UUID.fromString("c3f9b9a2-84f6-45c5-b4cf-8be7df2168a5"));
        contract.setOffer(offer);

        // Указываем UID заявки на товар
        Request request = new Request();
        request.setUid(UUID.fromString("69254c5f-a125-45f4-8798-7f5e23894b04"));
        contract.setRequest(request);

        contract.setCost(1240f);
        contract.setCount(890f);

        try {
            contract.setDateOfPerformance(new SimpleDateFormat("dd.MM.yyyy").parse("12.04.2020"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        body.setContent(contract);

        sender.sendEnvelope(envelope, "contract/add");
    }
}
