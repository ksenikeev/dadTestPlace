package ru.kpfu.icmit.association.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.icmit.association.model.Contract;
import java.util.List;
import java.util.UUID;

public interface ContractRepository extends CrudRepository<Contract, Long> {

    @Query("select o from Contract o where uid = :uid ")
    Contract findByUid(@Param("uid") UUID uid);

    @Query("select o from Contract o where o.nomenclature.uid = :uid ")
    List<Contract> findByNomenclatureUid(@Param("uid") UUID uid);

    @Query("select o from Contract o where o.request.uid = :uid ")
    List<Contract> findByRequestUid(@Param("uid") UUID uid);

    @Query("select o from Contract o where o.offer.uid = :uid ")
    List<Contract> findByOfferUid(@Param("uid") UUID uid);

    @Transactional
    Contract save(Contract contract);
}
