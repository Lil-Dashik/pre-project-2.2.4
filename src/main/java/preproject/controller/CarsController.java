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
    @Autowired
    private SortConfig sortConfig;

    @GetMapping
    public List<Car> listCars(@RequestParam(required = false) Integer count,
                              @RequestParam(required = false) List<String> sortBy,
                              Model model) {
        if (sortBy != null) {
            for (String field : sortBy) {
                if (!sortConfig.getEnabledFields().contains(field)) {
                    throw new IllegalArgumentException("Invalid sort field: " + field);
                }
            }
        }
        Sort sort = Sort.unsorted();
        if (sortBy != null && !sortBy.isEmpty()) {
            Sort.Order[] orders = sortBy.stream()
                    .map(Sort.Order::asc)
                    .toArray(Sort.Order[]::new);
            sort = Sort.by(orders);
        }
        List<Car> cars;
        if (count != null && count > 0) {
            cars = carService.listCarsSort(PageRequest.of(0, count, sort));
        } else {
            cars = carService.listCars();
        }
        model.addAttribute("cars", cars);
        return cars;
    }
}
