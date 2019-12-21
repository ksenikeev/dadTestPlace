package ru.kpfu.icmit.association.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.icmit.association.model.Offer;
import java.util.List;
import java.util.UUID;

public interface OfferRepository extends CrudRepository<Offer, Long> {

    @Query("select o from Offer o where uid = :uid ")
    Offer findByUid(@Param("uid") UUID uid);

    @Query("select o from Offer o where o.nomenclature.uid = :uid ")
    List<Offer> findByNomenclatureUid(@Param("uid") UUID uid);

    @Transactional
    Offer save(Offer offer);
}
