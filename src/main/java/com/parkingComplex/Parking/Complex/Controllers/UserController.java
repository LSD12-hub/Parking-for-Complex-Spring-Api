package com.parkingComplex.Parking.Complex.Controllers;

import com.parkingComplex.Parking.Complex.Entities.Car;
import com.parkingComplex.Parking.Complex.Entities.PersonType;
import com.parkingComplex.Parking.Complex.Entities.User;
import com.parkingComplex.Parking.Complex.Entities.UserWithCars;
import com.parkingComplex.Parking.Complex.Repositories.CarRepository;
import com.parkingComplex.Parking.Complex.Repositories.PersonTypeRepository;
import com.parkingComplex.Parking.Complex.Repositories.UserRepository;
import com.parkingComplex.Parking.Complex.Repositories.UserWIthCarsRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/User")
@RestController
public class UserController {

    public UserWIthCarsRepository userWithCarsRepo;
    public CarRepository carRepo;
    public UserRepository userRepo;
    public PersonTypeRepository personRepo;

    public UserController(UserWIthCarsRepository userWithCarsRepo, CarRepository carRepo, UserRepository userRepo, PersonTypeRepository personRepo) {
        this.userWithCarsRepo = userWithCarsRepo;
        this.carRepo = carRepo;
        this.userRepo = userRepo;
        this.personRepo = personRepo;
    }

    @GetMapping("/getTypeOfUserAndCars/{personType}")
    @Operation(
            summary = "Get a personType with their car(s)",
            description = "The personTypes : "
                    + "<ul><li>Visitor</li>"
                    + "<li>Tenant</li></ul>"
    )
    public List<UserWithCars> getUsers(String personType) {
        Optional<PersonType> person = personRepo.findBypersonType(personType);
        if (person.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Did not enter correct value for personType");

        return userWithCarsRepo.findBypersonTypeID(person.get().getPersonTypeID());
    }

    @Transactional
    @PostMapping("/createUser")
    public Map<String, Object> createUser(User user, Car car) {
        if (carRepo.existsBynumberPlate(car.getNumberPlate()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car already exists");

        if (!personRepo.existsBypersonTypeID(user.getPersonTypeID()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "PersonType doee not exist");

        User newUser = userRepo.save(user);

        car.setUserID(newUser.getId());

        carRepo.save(car);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("newUser", newUser);
        result.put("car", car);
        return result;
    }
}
