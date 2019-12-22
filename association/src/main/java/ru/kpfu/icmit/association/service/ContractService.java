package ru.kpfu.icmit.association.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.icmit.association.model.Contract;
import ru.kpfu.icmit.association.repository.ContractRepository;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    public List<Contract> findAll() {
        List<Contract> res = new ArrayList<>();
        contractRepository.findAll().forEach(res::add);
        return res;
    }
}
