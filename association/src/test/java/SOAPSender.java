import ru.kpfu.icmit.association.model.soap.Envelope;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SOAPSender {

    /**   */
    public static String LOCAL_URL  = "http://localhost:8080/association/";
    public static String REMOTE_URL = "http://185.20.227.163:8080/association/";

    private String baseUrl = LOCAL_URL;

    public SOAPSender(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * Пример отправки XML файла с данными конверта, получение конверта
     */
    public Envelope sendEnvelope(Envelope envelope, String resource) {

        String response = "";

        try {
            URL url = new URL( baseUrl + resource);

            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/xml");

            connection.setDoOutput(true);

            //Преобразуем класс-конверт в XML-документ
            String xmlContent = envelopeToXML(envelope);
            System.out.println("Send to " + url);
            System.out.println(xmlContent);

            try (OutputStream os = connection.getOutputStream()) {
                os.write(xmlContent.getBytes("UTF-8"));
                os.flush();
            }

            int rcode = connection.getResponseCode();
            System.out.println("Response return code: " + rcode);


            try (BufferedReader bf = new BufferedReader( new InputStreamReader(connection.getInputStream()))) {

                while (bf.ready()) {
                    response += bf.readLine();
                }

                System.out.println("Response body:\n" + response + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Преобразуем ответ в конверт
        if (response.length() > 0) {
            return readEnvelope(response);
        }
        return null;
    }

    /**
     * Пример отправки параметра, получение конверта
     */
    public Envelope sendParam(String paramName, String paramValue, String resource) {

        String response = "";

        try {
            URL url = new URL( baseUrl + resource);

            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            connection.setDoOutput(true);

            //Преобразуем класс-конверт в XML-документ
            String content = paramName+"=" + URLEncoder.encode(paramValue, "UTF-8");
            System.out.println("Send to " + url);
            System.out.println(content);

            try (OutputStream os = connection.getOutputStream()) {
                os.write(content.getBytes("UTF-8"));
                os.flush();
            }

            int rcode = connection.getResponseCode();
            System.out.println("Response return code: " + rcode);


            try (BufferedReader bf = new BufferedReader( new InputStreamReader(connection.getInputStream()))) {

                while (bf.ready()) {
                    response += bf.readLine();
                }

                System.out.println("Response body:\n" + response + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Преобразуем ответ в конверт
        if (response.length() > 0) {
            return readEnvelope(response);
        }
        return null;
    }

    /** Преобразование конверта в XML документ */
    private String envelopeToXML(Envelope envelope) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            JAXBContext context = JAXBContext.newInstance(Envelope.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // маршаллинг объекта в файл
            marshaller.marshal(envelope, bos);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return new String(bos.toByteArray());
    }

    /** Преобразование XML документа в класс-конверт */
    private Envelope readEnvelope(String content) {
        try(Reader reader = new StringReader(content)) {

            JAXBContext jaxbContext = JAXBContext.newInstance(Envelope.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            return (Envelope) un.unmarshal(reader);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
