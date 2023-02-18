package com.parkingComplex.Parking.Complex.Repositories;

import com.parkingComplex.Parking.Complex.Entities.CarWithUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CarWithUserRepository extends CrudRepository<CarWithUser, Long> {

    Optional<CarWithUser> findBynumberPlate (String NumberPlate);
}
