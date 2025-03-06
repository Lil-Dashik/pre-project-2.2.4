package preproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import preproject.config.SortConfig;
import preproject.model.Car;
import preproject.service.CarService;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarsController {

    @Autowired
    private CarService carService;

    @GetMapping
    public String listCars(@RequestParam(required = false, defaultValue = "0") Integer page,
                           @RequestParam(required = false) Integer count,
                           @RequestParam(required = false) List<String> sortBy,
                           Model model) {
        List<Car> cars;
        if (count != null && count > 0) {
            cars = carService.listCarsSort(page, count, sortBy);
        } else {
            cars = carService.listCars();
        }

        model.addAttribute("cars", cars);
        return "cars";
    }
}
