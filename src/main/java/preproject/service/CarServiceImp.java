package preproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import preproject.config.AppConfig;
import preproject.config.SortConfig;
import preproject.dao.CarRepository;
import preproject.model.Car;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImp implements CarService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private SortConfig sortConfig;

    @Autowired
    private AppConfig appConfig;

    @Override
    public void addCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public List<Car> listCarsSort(Integer page, Integer count, List<String> sortBy) {
        Sort sort = Sort.unsorted();
        if (sortBy != null && !sortBy.isEmpty()) {
            List<String> invalidFields = sortBy.stream()
                    .filter(field -> !sortConfig.getEnabledFields().contains(field))
                    .toList();
            if (!invalidFields.isEmpty()) {
                throw new IllegalArgumentException("Invalid sort field: " + sortBy);
            }
            List<Sort.Order> orders = sortBy.stream()
                    .map(Sort.Order::asc)
                    .toList();
            sort = Sort.by(orders);
        }
        Pageable pageable = PageRequest.of(page, count, sort);
        return carRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Car> listCars() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> listCarsByCount(int count) {
        int maxCar = appConfig.getMaxCar();
        if (count >= maxCar) {
            return carRepository.findAll();
        }
        Pageable pageable = PageRequest.of(0, count);
        return carRepository.listCarsByCount(count, pageable);
    }
}
