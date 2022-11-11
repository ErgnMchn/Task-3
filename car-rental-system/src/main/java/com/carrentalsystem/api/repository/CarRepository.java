package com.carrentalsystem.api.repository;

import com.carrentalsystem.api.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Long> {
}
