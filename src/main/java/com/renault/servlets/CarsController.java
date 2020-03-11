package com.renault.servlets;

import com.renault.model.Car;
import com.renault.service.CarsRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import java.util.List;

@RestController
@RequestMapping("/cars")
@CrossOrigin
public class CarsController extends HttpServlet {
    private CarsRepository carsRepository;

    public CarsController(CarsRepository carsRepository) {this.carsRepository = carsRepository;}

    @GetMapping("/{brand}")
    public List<Car> getByBrand(@PathVariable String brand) {
        return carsRepository.findByBrand(brand);
    }

    @GetMapping
    public List<Car> getAll() {
        return carsRepository.findAll();
    }
}
