package ru.kpfu.icmit.association.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.icmit.association.model.Metric;

public interface MetricRepository extends CrudRepository<Metric, Long> {

    @Transactional
    Metric save(Metric metric);
}
