package com.parkingComplex.Parking.Complex.Repositories;

import com.parkingComplex.Parking.Complex.Entities.Car;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Transactional
public interface CarRepository extends CrudRepository<Car, Long> {

    boolean existsBynumberPlate(String numberPlate);
}
