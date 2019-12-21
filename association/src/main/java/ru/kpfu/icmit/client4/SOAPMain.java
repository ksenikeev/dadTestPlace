package ru.kpfu.icmit.client4;

import ru.kpfu.icmit.association.model.Nomenclature;
import ru.kpfu.icmit.association.model.Offer;
import ru.kpfu.icmit.association.model.Organization;
import ru.kpfu.icmit.association.model.Request;
import ru.kpfu.icmit.association.model.soap.Body;
import ru.kpfu.icmit.association.model.soap.Envelope;
import ru.kpfu.icmit.association.model.soap.Header;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

public class SOAPMain {

    public static void main(String[] args) {

        SOAPSender sender = new SOAPSender();

        createEnvelopeRequest();
        sender.sendFile(new File("request.xml"), "request/add");

        createEnvelopeOffer();
        sender.sendFile(new File("offer.xml"), "offer/add");

        createEnveopeNomenclature("Уголь антрацит");
        sender.sendFile(new File("nomenclature.xml"), "nomenclature/add");
        List<Nomenclature> lst = sender.getNomenclatures();
        lst.forEach(System.out::println);

        createEnvelopeOrganization();
        sender.sendFile(new File("organization.xml"), "organization/add");

        createEnvelopeRequest();
        sender.sendFile(new File("request.xml"), "request/add");
    }


    /**
     * Создаем конверт для отправки данных об организации
     */
    public static void createEnvelopeOrganization() {
        Envelope envelope = new Envelope();
        Header header = new Header();
        Body body = new Body();
        envelope.setHeader(header);
        envelope.setBody(body);

        Organization organization = new Organization();
        organization.setUid(UUID.randomUUID());
        organization.setNameOfOrganization("Производитель 2");
        organization.setInn("1600000002");
        organization.setKpp("1601001");
        organization.setAdressOfOrganization("г. Казань, ул. Университетская, д. 35");
        body.setContent(organization);
        saveEnvelopeToFile(envelope, "organization.xml");
    }

    /**
     * Создаем конверт для отправки запроса на товары/услуги
     */
    public static void createEnvelopeRequest() {
        Envelope envelope = new Envelope();
        Header header = new Header();
        Body body = new Body();
        envelope.setHeader(header);
        envelope.setBody(body);

        Organization organization = new Organization();
        organization.setInn("1600000002");
        organization.setKpp("1601001");

        Request request = new Request();
        request.setUid(UUID.randomUUID());
        request.setOrganization(organization);

        Nomenclature nomenclature = new Nomenclature();
        nomenclature.setUid(UUID.fromString("058b8777-1bc1-4b9c-8c95-34f0f3bd2623"));

        request.setNomenclature(nomenclature);

        try {
            request.setDateOfPerformance(new SimpleDateFormat("dd.MM.yyyy").parse("12.01.2020"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        request.setCountOfProduct(Float.valueOf(100));

        request.setPriceOfProduct(Float.valueOf(1250));

        request.setUnitCode("piece");

        body.setContent(request);
        saveEnvelopeToFile(envelope, "request.xml");
    }

    /**
     * Создаем конверт для отправки запроса на товары/услуги
     */
    public static void createEnvelopeOffer() {
        Envelope envelope = new Envelope();
        Header header = new Header();
        Body body = new Body();
        envelope.setHeader(header);
        envelope.setBody(body);

        Organization organization = new Organization();
        organization.setInn("1600000002");
        organization.setKpp("1601001");

        Offer offer = new Offer();
        offer.setUid(UUID.randomUUID());
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
        saveEnvelopeToFile(envelope, "offer.xml");
    }

    public static void createEnveopeNomenclature(String nomenclatureName) {

        Envelope envelope = new Envelope();
        Header header = new Header();
        Body body = new Body();
        envelope.setHeader(header);
        envelope.setBody(body);

        Nomenclature nomenclature = new Nomenclature(nomenclatureName, null, null);
        nomenclature.setUid(UUID.randomUUID());

        body.setContent(nomenclature);
        saveEnvelopeToFile(envelope, "nomenclature.xml");
    }

    public static Envelope readEnvelope() {
        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(Envelope.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            return (Envelope) un.unmarshal(new File("nomenclature.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
   }

   public static void saveEnvelopeToFile(Envelope envelope, String fname) {
       try {
           JAXBContext context = JAXBContext.newInstance(Envelope.class);
           Marshaller marshaller = context.createMarshaller();
           // устанавливаем флаг для читабельного вывода XML в JAXB
           marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

           // маршаллинг объекта в файл
           marshaller.marshal(envelope, new File(fname));
       } catch (JAXBException e) {
           e.printStackTrace();
       }
   }
}
