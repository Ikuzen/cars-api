package com.renault.initializer;

import com.renault.model.Car;
import com.renault.service.CarsRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class DatabaseInitializerService {

    private final CarsRepository repository;

    public DatabaseInitializerService(CarsRepository repository) {
        this.repository = repository;
    }

    public void initializeDB() {
        List<Car> cars = getCars();
        repository.saveAll(cars);
    }

    private List<Car> getCars() {
        List<Car> cars = new ArrayList<>();
        for (String line : getCarsFromCsvFile()) {
            String[] column = line.split(";");
            String carBrand = column[0].replace("\"", "");
            String carModel = column[1].replace("\"", "").strip();
            Car car = new Car(carBrand, carModel);
            cars.add(car);
        }
        return cars;
    }

    private List<String> getCarsFromCsvFile() {
        InputStream resource = DatabaseInitializerService.class.getResourceAsStream("cars.csv");
        return new BufferedReader(new InputStreamReader(resource)).lines().collect(toList());
    }

}
