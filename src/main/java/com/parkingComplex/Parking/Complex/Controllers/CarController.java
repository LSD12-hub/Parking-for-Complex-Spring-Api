package com.parkingComplex.Parking.Complex.Controllers;

import com.parkingComplex.Parking.Complex.Entities.Car;
import com.parkingComplex.Parking.Complex.Entities.CarWithUser;
import com.parkingComplex.Parking.Complex.Repositories.CarRepository;
import com.parkingComplex.Parking.Complex.Repositories.CarWithUserRepository;
import com.parkingComplex.Parking.Complex.Repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarController {

    CarWithUserRepository carWithUserRepo;
    CarRepository carRepo;
    UserRepository userRepo;

    public CarController(CarWithUserRepository carWithUserRepo, CarRepository carRepo, UserRepository userRepo) {
        this.carWithUserRepo = carWithUserRepo;
        this.carRepo = carRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/getCarOwner/{numberPlate}")
    public CarWithUser getCarOwner(String numberPlate) {
        Optional<CarWithUser> car = carWithUserRepo.findBynumberPlate(numberPlate);

        if(car.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car does not exist");

        return car.get();
    }

    @PostMapping("/addCarForExistingUser/")
    public Car addCarForExistingUser(Car car, Long userID) {
        if (carRepo.existsBynumberPlate(car.getNumberPlate()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car already exists");

        if (!userRepo.existsById(userID))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");

        car.setUserID(userID);

        return carRepo.save(car);
    }
}
