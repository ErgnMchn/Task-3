package com.carrentalsystem.api.service;

import com.carrentalsystem.api.model.Car;

import java.util.List;

public interface CarService {
    List<Car> listAllCars();

    void createCar(Car car);

    Car editCarById(long id, Car newCar);

    List<Car> checkAllAvailableCars();

    void rentCar(long id);

}
