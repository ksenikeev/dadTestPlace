import ru.kpfu.icmit.association.model.*;
import ru.kpfu.icmit.association.model.soap.Envelope;
import java.util.List;

public class GetNewNomenclatureAfterDate {

    public static void main(String[] args) {

        //SOAPSender sender = new SOAPSender(SOAPSender.REMOTE_URL);
        SOAPSender sender = new SOAPSender(SOAPSender.LOCAL_URL);

        Envelope responseEnvelope = sender.sendParam(
                "datefrom", "2019-01-01T00:00:01.0+03:00","nomenclature/get");
        NomenclatureList nomenclatureList = (NomenclatureList) responseEnvelope.getBody().getContent();

        List<Nomenclature> lst = nomenclatureList.getNomenclatureList();
        lst.forEach(System.out::println);
    }
}
