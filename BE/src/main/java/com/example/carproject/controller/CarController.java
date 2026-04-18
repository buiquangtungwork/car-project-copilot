package com.example.carproject.controller;

import com.example.carproject.entity.Car;
import com.example.carproject.service.CarService;
import com.example.carproject.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private FileService fileService;

    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Optional<Car> car = carService.getCarById(id);
        return car.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carService.saveCar(car);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car carDetails) {
        Optional<Car> car = carService.getCarById(id);
        if (car.isPresent()) {
            Car existingCar = car.get();
            existingCar.setMake(carDetails.getMake());
            existingCar.setModel(carDetails.getModel());
            existingCar.setYear(carDetails.getYear());
            Car updatedCar = carService.saveCar(existingCar);
            return ResponseEntity.ok(updatedCar);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        if (carService.getCarById(id).isPresent()) {
            carService.deleteCar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/import")
    public ResponseEntity<String> importCars(@RequestParam("file") MultipartFile file) {
        try {
            List<Car> cars;
            String fileName = file.getOriginalFilename();

            if (fileName != null && fileName.endsWith(".csv")) {
                cars = fileService.parseCsvFile(file);
            } else if (fileName != null && (fileName.endsWith(".xlsx") || fileName.endsWith(".xls"))) {
                cars = fileService.parseExcelFile(file);
            } else {
                return ResponseEntity.badRequest().body("Unsupported file format. Please upload CSV or Excel files.");
            }

            int importedCount = 0;
            for (Car car : cars) {
                if (car.getMake() != null && !car.getMake().isEmpty() &&
                    car.getModel() != null && !car.getModel().isEmpty() &&
                    car.getYear() > 0) {
                    carService.saveCar(car);
                    importedCount++;
                }
            }

            return ResponseEntity.ok("Successfully imported " + importedCount + " cars out of " + cars.size() + " records.");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error processing file: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid file format or data: " + e.getMessage());
        }
    }

    @GetMapping("/export/csv")
    public ResponseEntity<InputStreamResource> exportCarsCsv() {
        try {
            List<Car> cars = carService.getAllCars();
            ByteArrayInputStream inputStream = fileService.exportCarsToCsv(cars);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=cars.csv");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("text/csv"))
                    .body(new InputStreamResource(inputStream));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/export/excel")
    public ResponseEntity<InputStreamResource> exportCarsExcel() {
        try {
            List<Car> cars = carService.getAllCars();
            ByteArrayInputStream inputStream = fileService.exportCarsToExcel(cars);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=cars.xlsx");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(new InputStreamResource(inputStream));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/template/csv")
    public ResponseEntity<InputStreamResource> downloadCsvTemplate() {
        try {
            ByteArrayInputStream inputStream = fileService.generateCsvTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=car_template.csv");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("text/csv"))
                    .body(new InputStreamResource(inputStream));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/template/excel")
    public ResponseEntity<InputStreamResource> downloadExcelTemplate() {
        try {
            ByteArrayInputStream inputStream = fileService.generateExcelTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=car_template.xlsx");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(new InputStreamResource(inputStream));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}