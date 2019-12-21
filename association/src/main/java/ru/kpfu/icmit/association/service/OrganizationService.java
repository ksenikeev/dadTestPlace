package ru.kpfu.icmit.association.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.icmit.association.model.Organization;
import ru.kpfu.icmit.association.repository.OrganizationRepository;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    public List<Organization> findAll() {
        List<Organization> res = new ArrayList<>();
        organizationRepository.findAll().forEach(res::add);
        return res;
    }
}
