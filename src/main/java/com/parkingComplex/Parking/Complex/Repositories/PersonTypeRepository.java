package com.parkingComplex.Parking.Complex.Repositories;


import com.parkingComplex.Parking.Complex.Entities.PersonType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonTypeRepository extends CrudRepository<PersonType, Long> {

    boolean existsBypersonTypeID(int id);

    Optional<PersonType> findBypersonType(String personType);
}
