package com.parkingComplex.Parking.Complex.Repositories;

import com.parkingComplex.Parking.Complex.Entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
