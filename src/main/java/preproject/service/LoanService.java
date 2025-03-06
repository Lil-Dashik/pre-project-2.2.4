package preproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import preproject.config.LoanConfig;
import preproject.config.UrlConfig;
import preproject.dao.UserRepository;
import preproject.model.Car;
import preproject.model.IncomeResponse;
import preproject.model.User;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Service
public class LoanService {


    @Autowired
    private LoanConfig loanConfig;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UrlConfig urlConfig;

    public double getApprovedLoan(Long userId) {
        Double userIncome = getUserIncomeFromApi(userId);
        if (userIncome == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с ID " + userId + " не найден.");
        }
        Car userCar = userRepository.findUserWithCar(userId)
                .map(User::getCar)
                .orElse(null);

        double maxLoanFromIncome = userIncome >= loanConfig.getMinimalIncome()
                ? userIncome * 12 * loanConfig.getMaxCreditRateFromIncome()
                : 0.0;

        double maxLoanFromCar = (userCar != null && userCar.getPrice() > loanConfig.getMinCarPriceForLoan())
                ? userCar.getPrice() * loanConfig.getMaxCreditRateFromCarPrice()
                : 0.0;

        return Math.max(maxLoanFromIncome, maxLoanFromCar);
    }
    private Double getUserIncomeFromApi(Long userId) {
        try {
            ResponseEntity<IncomeResponse[]> response = restTemplate.getForEntity(urlConfig.getIncomes(), IncomeResponse[].class);
            IncomeResponse[] incomes = response.getBody();
            if (incomes == null || incomes.length == 0) {
                return null;
            }
            return Optional.ofNullable(response.getBody())
                    .stream()
                    .flatMap(Arrays::stream)
                    .filter(i -> Objects.equals(i.getId(), userId))
                    .map(IncomeResponse::getIncome)
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            System.out.println("Error when receiving income: " + e.getMessage());
            return 0.0;
        }
    }
}

