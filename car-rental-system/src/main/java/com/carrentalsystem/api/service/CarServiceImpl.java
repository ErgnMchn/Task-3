package com.carrentalsystem.api.service;

import com.carrentalsystem.api.exception.CarNotFoundException;
import com.carrentalsystem.api.model.Car;
import com.carrentalsystem.api.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements com.carrentalsystem.api.service.CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> listAllCars() {
        List<Car> cars = carRepository.findAll();

        return cars;
    }

    public List<Car> checkAllAvailableCars() {
        List<Car> availableCars = carRepository.findAll().stream().filter(car -> car.isAvailable()).toList();

        return availableCars;
    }

    public void createCar(Car car) {
        carRepository.save(car);
    }

    public Car editCarById(long id, Car newCar) {
        Car existingCar = carRepository.findById(id).orElse(null);
        if (existingCar == null) {
            throw new CarNotFoundException("No Such car is exist");
        } else {
            existingCar.setName(newCar.getName());
            existingCar.setModel(newCar.getModel());
            existingCar.setAvailable(newCar.isAvailable());

            return carRepository.save(existingCar);
        }

    }

    public void rentCar(long id) {
        Car requestedCar = carRepository.findById(id).orElse(null);

        if (requestedCar.isAvailable()) {
            requestedCar.setAvailable(false);

            carRepository.save(requestedCar);
        } else if (requestedCar == null) {

            throw new CarNotFoundException("Car Not Found");

        } else {
            System.out.println("Car is already rented");
        }
    }
}
