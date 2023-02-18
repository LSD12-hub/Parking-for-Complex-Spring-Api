package com.parkingComplex.Parking.Complex.Repositories;

import com.parkingComplex.Parking.Complex.Entities.ParkingTicket;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ParkingTicketRepository extends CrudRepository<ParkingTicket, Long> {

    List<ParkingTicket> findBysectionid(Long aLong);
    List<ParkingTicket> findBysectionidAndDepartureDateIsNull(Long aLong);

    List<ParkingTicket> findBynumberPlate(String numberPlate);

    boolean existsBynumberPlate(String numberPlate);
}
