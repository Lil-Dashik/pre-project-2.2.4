package preproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import preproject.config.LoanConfig;
import preproject.config.UrlConfig;
import preproject.dao.UserRepository;
import preproject.model.Car;
import preproject.model.IncomeResponse;
import preproject.model.User;
import preproject.model.UserIncome;

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
        Optional<Double> optionalUserIncome = getUserIncomeFromApi(userId);
        if (optionalUserIncome.isEmpty()) {
            throw new IllegalArgumentException("The user with the ID " + userId + " was not found in the system.");
        }
        double userIncome = optionalUserIncome.get();
        Optional<User> optionalUser = userRepository.findUserWithCar(userId);

        Car userCar = optionalUser.map(User::getCar).orElse(null);
        double maxLoanFromIncome = 0;
        double maxLoanFromCar = 0;

        if (userIncome >= loanConfig.getMinimalIncome()) {
            maxLoanFromIncome = userIncome * 12 * loanConfig.getMaxCreditRateFromIncome();
        }

        if (userCar != null && userCar.getPrice() > loanConfig.getMinCarPriceForLoan()) {
            maxLoanFromCar = userCar.getPrice() * loanConfig.getMaxCreditRateFromCarPrice();
        }

        double approvedLoan = Math.max(maxLoanFromIncome, maxLoanFromCar);

        return approvedLoan > 0 ? approvedLoan : 0;
    }
    private Optional<Double> getUserIncomeFromApi(Long userId) {
        try {
            String apiUrl = urlConfig.getIncomes();
            ResponseEntity<IncomeResponse[]> response = restTemplate.getForEntity(apiUrl, IncomeResponse[].class);
            IncomeResponse[] incomes = response.getBody();

            if (incomes == null || incomes.length == 0) {
                return Optional.empty();
            }

            return Arrays.stream(incomes)
                    .filter(i -> Objects.equals(i.getId(), userId))
                    .map(IncomeResponse::getIncome)
                    .findFirst();
        } catch (Exception e) {
            System.out.println(" Error when receiving user income: " + e.getMessage());
            return Optional.empty();
        }
    }
}

