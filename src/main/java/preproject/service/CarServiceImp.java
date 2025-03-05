package preproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import preproject.config.AppConfig;
import preproject.dao.CarRepository;
import preproject.model.Car;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class CarServiceImp implements CarService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private AppConfig appConfig;

    @Override
    public void addCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public List<Car> listCarsSort(Pageable pageable) {
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
