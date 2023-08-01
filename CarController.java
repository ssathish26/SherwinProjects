package com.example.SimplestCRUDExample.controller;

import com.example.SimplestCRUDExample.model.Car;
import com.example.SimplestCRUDExample.repo.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CarController {

    @Autowired
    CarRepository carRepository;

    @GetMapping("/getAllCars")
    public ResponseEntity<List<Car>> getAllCars() {
        try {
            List<Car> carList = new ArrayList<>();
            carRepository.findAll().forEach(carList::add);

            if (carList.isEmpty()) {
                return new ResponseEntity<>(carList, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(carList, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getCarById/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Optional<Car> carObj = carRepository.findById(id);
        if (carObj.isPresent()) {
            return new ResponseEntity<>(carObj.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addCar")
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        try {
            Car carObj = carRepository.save(car);
            return new ResponseEntity<>(carObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateCar/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car car) {
        try {
            Optional<Car> carData = carRepository.findById(id);
            if (carData.isPresent()) {
                Car updatedCarData = carData.get();
                updatedCarData.setModel(car.getModel());
                updatedCarData.setYearmade(car.getYearmade());

                Car carObj = carRepository.save(updatedCarData);
                return new ResponseEntity<>(carObj, HttpStatus.CREATED);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteCarById/{id}")
    public ResponseEntity<HttpStatus> deleteCar(@PathVariable Long id) {
        try {
            carRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteAllCars")
    public ResponseEntity<HttpStatus> deleteAllCars() {
        try {
            carRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
