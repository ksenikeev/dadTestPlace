package ru.kpfu.icmit.association.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.icmit.association.model.Organization;
import java.util.UUID;

public interface OrganizationRepository extends CrudRepository<Organization, Long> {

    @Query("select o from Organization o where uid = :uid ")
    Organization findByUid(@Param("uid") UUID uid);

    @Query("select o from Organization o where nameOfOrganization = :nameOfOrganization ")
    Organization findByName(@Param("nameOfOrganization") String nameOfOrganization);

    @Query("select o from Organization o where inn = :inn ")
    Organization findByInn(@Param("inn") String inn);

    @Transactional
    Organization save(Organization organization);
}
