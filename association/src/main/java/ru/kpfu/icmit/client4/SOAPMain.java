package ru.kpfu.icmit.client4;

import ru.kpfu.icmit.association.model.Nomenclature;
import ru.kpfu.icmit.association.model.Organization;
import ru.kpfu.icmit.association.model.soap.Body;
import ru.kpfu.icmit.association.model.soap.Envelope;
import ru.kpfu.icmit.association.model.soap.Header;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;
import java.util.UUID;

public class SOAPMain {

    public static void main(String[] args) {

        SOAPSender sender = new SOAPSender();

        //createEnveopeNomenclature("Телевизор");
        //sender.sendFile(new File("nomenclature.xml"), "addnomenclature");
        //List<Nomenclature> lst = sender.getNomenclatures();
        //lst.forEach(System.out::println);

        createEnvelopeOrganization();

        sender.sendFile(new File("organization.xml"), "addorganization");

    }


    public static void createEnvelopeOrganization() {
        Envelope envelope = new Envelope();
        Header header = new Header();
        Body body = new Body();
        envelope.setHeader(header);
        envelope.setBody(body);

        Organization organization = new Organization();
        organization.setUid(UUID.randomUUID());
        organization.setNameOfOrganization("Производитель 1");
        organization.setInn("1600000001");
        organization.setKpp("1601001");
        organization.setAdressOfOrganization("г. Казань, ул. Университетская, д. 35");
        body.setContent(organization);
        saveEnvelopeToFile(envelope, "organization.xml");
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
