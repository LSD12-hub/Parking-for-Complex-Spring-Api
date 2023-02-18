package com.parkingComplex.Parking.Complex.Controllers;

import com.parkingComplex.Parking.Complex.Entities.ParkingTicket;
import com.parkingComplex.Parking.Complex.Entities.Section;
import com.parkingComplex.Parking.Complex.Repositories.CarRepository;
import com.parkingComplex.Parking.Complex.Repositories.ParkingTicketRepository;
import com.parkingComplex.Parking.Complex.Repositories.SectionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    SectionRepository sectionRepo;
    CarRepository carRepo;
    ParkingTicketRepository parkRepo;

    public ParkingController(SectionRepository sectionRepo, CarRepository carRepo, ParkingTicketRepository parkRepo) {
        this.sectionRepo = sectionRepo;
        this.carRepo = carRepo;
        this.parkRepo = parkRepo;
    }

    @GetMapping("/parkingAreaAndCapacity")
    public Iterable<Section> getParkingAreaAndCapacity() {
        return sectionRepo.findAll();
    }

    @GetMapping("/availablePakingForSection/{sectionID}")
    public Map<String, Object> getAvailableParking(Long sectionid) {
        Optional<Section> section = sectionRepo.findById(sectionid);

        if (section.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        List<ParkingTicket> parkingTickets = parkRepo.findBysectionidAndDepartureDateIsNull(sectionid);//.stream()
//                .filter((n) -> n.getDepartureTime() == null)
//                .toList(;

        int amountOfParking = section.get().getCapacity() - parkingTickets.size();

        Map<String, Object> results = new HashMap<>();
        results.put("amountOfParking", amountOfParking);
        results.put("section", section.get().getSection());

        return results;
    }

    @GetMapping("/getAllParkingTickets")
    public Iterable<ParkingTicket> getParkingTickets() {
        return parkRepo.findAll();
    }

    @PatchMapping("/updateParkingTicket/{parkingTicketID}/")
    public ParkingTicket updateParkingTicket(Long parkingTicketID) {
        Date date = new Date();

        Optional<ParkingTicket> parkingTicket = parkRepo.findById(parkingTicketID);

        if (parkingTicket.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Parking ticket does not exist");

        ParkingTicket updateParkingTicket1 = parkingTicket.get();

        if (updateParkingTicket1.getDepartureTime() != null)
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Cannot Update this parking ticket because it has date");

        updateParkingTicket1.setDepartureTime(date);

        return parkRepo.save(updateParkingTicket1);
    }

    @PostMapping("/createNewParkingTicket")
    public ParkingTicket createNewParkingTicket(ParkingTicket newParkingTicket) {
        if (!sectionRepo.existsById(newParkingTicket.getSectionid()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Section on found");

        if (!carRepo.existsBynumberPlate(newParkingTicket.getNumberPlate()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found");

        if (!parkRepo.existsBynumberPlate(newParkingTicket.getNumberPlate()))
            return setArrivalDateAndSaveToDatabase(newParkingTicket);

        boolean doesCarHaveAnyOpenTickets = parkRepo.findBynumberPlate(newParkingTicket.getNumberPlate())
                        .stream().filter((n) -> n.getDepartureTime() == null)
                        .toList()
                        .isEmpty();

        if (!doesCarHaveAnyOpenTickets)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car has open tickets");


        return setArrivalDateAndSaveToDatabase(newParkingTicket);
    }

    public ParkingTicket setArrivalDateAndSaveToDatabase(ParkingTicket parkingTicket) {
        parkingTicket.setArrivalTime(new Date());
        return parkRepo.save(parkingTicket);
    }
}
