package ru.kpfu.icmit.client4;

import ru.kpfu.icmit.association.model.Nomenclature;
import ru.kpfu.icmit.association.model.NomenclatureList;
import ru.kpfu.icmit.association.model.soap.Envelope;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

public class SOAPSender {

    /**
     * Пример отправки XML файла с данными конверта
     * @param file
     */
    public void sendFile(File file, String resource) {

        try {
            //URL url = new URL("http://185.20.227.163:8080/association/" + resource);
            URL url = new URL("http://localhost:8080/association/" + resource);

            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/xml");

            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream();
                 FileInputStream fis = new FileInputStream(file)) {

                byte[] buf = new byte[2048];
                int r = 0;
                while ((r = fis.read(buf)) != -1) {
                    os.write(buf,0,r);
                }

                os.flush();
            }

            int rcode = connection.getResponseCode();
            System.out.println(rcode);

            String env = "";
            try (BufferedReader bf = new BufferedReader( new InputStreamReader(connection.getInputStream()))) {

                while (bf.ready()) {
                    env += bf.readLine();
                }

                System.out.println("response: " + env);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Nomenclature> getNomenclatures() {

        try {
            //URL url = new URL("http://185.20.227.163:8080/association/nomenclature/get");
            URL url = new URL("http://localhost:8080/association/nomenclature/get");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            try (OutputStream os = connection.getOutputStream()) {
                os.write(("datefrom=2019-01-01T00:00:00.001+03:00").getBytes("UTF-8"));
            }

            int rcode = connection.getResponseCode();
            System.out.println(rcode);

            String env = "";
            try (BufferedReader bf = new BufferedReader( new InputStreamReader(connection.getInputStream()))) {

                while (bf.ready()) {
                    env += bf.readLine();
                }

                System.out.println("response: " + env);

                try {
                    // создаем объект JAXBContext - точку входа для JAXB
                    JAXBContext jaxbContext = JAXBContext.newInstance(Envelope.class);
                    Unmarshaller un = jaxbContext.createUnmarshaller();

                    Envelope envelope = (Envelope) un.unmarshal(new ByteArrayInputStream(env.getBytes(Charset.forName("UTF-8"))));
                    NomenclatureList xmlList = (NomenclatureList) envelope.getBody().getContent();
                    return xmlList.getNomenclatureList();
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
