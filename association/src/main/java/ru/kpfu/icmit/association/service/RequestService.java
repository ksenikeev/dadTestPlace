package ru.kpfu.icmit.association.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.icmit.association.model.Request;
import ru.kpfu.icmit.association.repository.RequestRepository;
import java.util.ArrayList;
import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public List<Request> findAll() {
        List<Request> res = new ArrayList<>();
        requestRepository.findAll().forEach(res::add);
        return res;
    }
}
