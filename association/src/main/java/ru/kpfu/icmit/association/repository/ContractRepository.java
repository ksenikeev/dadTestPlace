package ru.kpfu.icmit.association.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.icmit.association.model.Contract;

public interface ContractRepository extends CrudRepository<Contract, Long> {

}
