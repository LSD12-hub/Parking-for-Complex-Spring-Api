package com.parkingComplex.Parking.Complex.Repositories;

import com.parkingComplex.Parking.Complex.Entities.UserWithCars;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@EnableTransactionManagement
@Transactional
public interface UserWIthCarsRepository extends CrudRepository<UserWithCars, Long> {

    List<UserWithCars> findBypersonTypeID(int id);

    List<UserWithCars> findBypersonTypeID(Long personTypeID);
}
