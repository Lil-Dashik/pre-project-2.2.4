package preproject.service;

import preproject.model.Car;

import java.util.List;

public interface CarService {
    void addCar(Car car);

    List<Car> listCars();

    List<Car> listCarsByCount(int count);

    List<Car> listCarsSort(Integer page, Integer count, List<String> sortBy);
}
