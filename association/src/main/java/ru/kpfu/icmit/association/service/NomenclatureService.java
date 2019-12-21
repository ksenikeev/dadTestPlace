package ru.kpfu.icmit.association.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.icmit.association.model.Nomenclature;
import ru.kpfu.icmit.association.repository.NomenclatureRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NomenclatureService {

    @Autowired
    private NomenclatureRepository nomenclatureRepository;

    public List<Nomenclature> getNomenclature(Date dateFrom) {
        return nomenclatureRepository.getNomenclature(dateFrom);
    }

    public Nomenclature save(Nomenclature nomenclature) {
        return nomenclatureRepository.save(nomenclature);
    }

    public List<Nomenclature> findAll() {
        List<Nomenclature> res = new ArrayList<>();
        nomenclatureRepository.findAll().forEach(res::add);
        return res;
    }
}
