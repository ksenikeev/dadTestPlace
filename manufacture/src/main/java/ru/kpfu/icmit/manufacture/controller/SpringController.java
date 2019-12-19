package ru.kpfu.icmit.manufacture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.icmit.manufacture.model.Nomenclature;
import ru.kpfu.icmit.manufacture.model.soap.Body;
import ru.kpfu.icmit.manufacture.model.soap.Envelope;
import ru.kpfu.icmit.manufacture.model.soap.Header;
import ru.kpfu.icmit.manufacture.service.NomenclatureService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class SpringController {


    @Autowired
    private NomenclatureService nomenclatureService;

    @RequestMapping(value = "/nomenclature")
    public String getNomenclatureList(@RequestParam(required = false) String dateFrom,
                                      @ModelAttribute("model") ModelMap model) {

        if (dateFrom == null || dateFrom.length() == 0) {
            dateFrom = "2019-01-01T00:00:00.0+03:00";
        }

        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SXXX").parse(dateFrom);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Nomenclature> nomenclatures = nomenclatureService.getNomenclature(date);

        model.addAttribute("reqdate", dateFrom);
        model.addAttribute("nomenclatures", nomenclatures);

        return "/dict/nomenclature";
    }


    @RequestMapping(value = "/addnomenclature", method = RequestMethod.GET)
    public String addNomenclature(@ModelAttribute("model") ModelMap model) {

        return "/dict/addnomenclature";
    }

    @RequestMapping(value = "/ы", method = RequestMethod.POST)
    public String addNewNom(
            @ModelAttribute("model") ModelMap model,
            @ModelAttribute Nomenclature body
                            ) {
        String env = createEnveope(body);

        System.out.println(env);

        env = sendNomenclature(env);

        System.out.println(env);

        Nomenclature xml = null;
        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(Envelope.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            Envelope envelope = (Envelope) un.unmarshal(new ByteArrayInputStream(env.getBytes(Charset.forName("UTF-8"))));
            xml = (Nomenclature) envelope.getBody().getContent();

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        if (xml != null) {
            nomenclatureService.save(xml);
            System.out.println("success!");
        }

        return "/dict/nomsuccess";
    }

    public class NomenclatureForm {
        public String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @RequestMapping(value = "/pagenomenclature")
    public String getNomenclatureList(@RequestParam(required = false, defaultValue = "0") int from,
                                      @RequestParam(required = false, defaultValue = "100") int to,
                                      @ModelAttribute("model") ModelMap model) {

        System.out.println();

        List<Nomenclature> nomenclatures = nomenclatureService.getNomenclatureFromTo(from, to);

        model.addAttribute("reqdate", "");
        model.addAttribute("nomenclatures", nomenclatures);

        return "/dict/nomenclature";
    }

    public String createEnveope(Nomenclature nomenclature) {

        if (nomenclature.getUid() == null ) {
            nomenclature.setUid(UUID.randomUUID());
        }

        Envelope envelope = new Envelope();
        Header header = new Header();
        Body body = new Body();
        envelope.setHeader(header);
        envelope.setBody(body);

        body.setContent(nomenclature);

        String result = "";

        try (ByteArrayOutputStream os = new ByteArrayOutputStream()){
            JAXBContext context = JAXBContext.newInstance(Envelope.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // маршаллинг объекта в файл
            marshaller.marshal(envelope, os);

            result = new String(os.toByteArray());
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public String sendNomenclature(String envelope) {

        String env = "";

        try {

            //URL url = new URL("http://185.20.227.163:8080/association/addnomenclature");
            URL url = new URL("http://localhost:8080/association/addnomenclature");

            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/xml");

            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                os.write(envelope.getBytes("UTF-8"));
                os.flush();
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        int rcode = connection.getResponseCode();
            System.out.println(rcode);

            try (BufferedReader bf = new BufferedReader( new InputStreamReader(connection.getInputStream()))) {

                while (bf.ready()) {
                    env += bf.readLine();
                }

                System.out.println("response: " + env);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return env;
    }
}