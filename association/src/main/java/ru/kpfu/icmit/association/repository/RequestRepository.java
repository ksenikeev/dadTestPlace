package ru.kpfu.icmit.association.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.icmit.association.model.Organization;
import ru.kpfu.icmit.association.model.Request;

import java.util.List;
import java.util.UUID;

public interface RequestRepository extends CrudRepository<Request, Long> {

    @Query("select r from Request r where uid = :uid ")
    Request findByUid(@Param("uid") UUID uid);

    @Query("select r from Request r where organization = :organization ")
    List<Request> findByOrganization(@Param("organization") Organization organization);

    @Transactional
    Request save(Request request);
}
