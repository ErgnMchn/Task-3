package com.carrentalsystem.api.controller;

import com.carrentalsystem.api.exception.CarNotFoundException;
import com.carrentalsystem.api.exception.ErrorResponse;
import com.carrentalsystem.api.model.Car;
import com.carrentalsystem.api.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping()
    public List<Car> listAllCars() {

        return carService.listAllCars();
    }

    @GetMapping("/availableCars")
    public List<Car> findAllAvailableCars() {
        return carService.checkAllAvailableCars();
    }

    @PostMapping
    public void createCar(@RequestBody Car car) {
        carService.createCar(car);

    }

    @PutMapping("/{id}")
    public Car editCar(@RequestBody Car car, @PathVariable Long id) {
        return carService.editCarById(id, car);
    }

    @PutMapping("rent/{id}")
    public void rentCar(@PathVariable Long id) {
        carService.rentCar(id);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = CarNotFoundException.class)
    public ErrorResponse handleCarNotFoundException(CarNotFoundException e) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
    }
}
