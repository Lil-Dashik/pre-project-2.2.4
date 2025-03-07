package preproject.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import preproject.config.LoanConfig;
import preproject.dao.UserRepository;
import preproject.model.Car;
import preproject.model.User;

@RequiredArgsConstructor
@Service
public class LoanService {
    private final UserRepository userRepository;
    private final LoanConfig loanConfig;
    private final IncomeService incomeService;

    public double getApprovedLoan(Long userId) {
        double userIncome = incomeService.getUserIncome(userId);

        Car userCar = userRepository.findUserWithCar(userId)
                .map(User::getCar)
                .orElse(null);
        double maxLoanFromIncome;
        if (userIncome >= loanConfig.getMinimalIncome()) {
            maxLoanFromIncome = userIncome * 12 * loanConfig.getMaxCreditRateFromIncome();
        } else {
            maxLoanFromIncome = 0.0;
        }
        double maxLoanFromCar;
        if (userCar != null && userCar.getPrice() > loanConfig.getMinCarPriceForLoan()) {
            maxLoanFromCar = userCar.getPrice() * loanConfig.getMaxCreditRateFromCarPrice();
        } else {
            maxLoanFromCar = 0.0;
        }
        return Math.max(maxLoanFromIncome, maxLoanFromCar);
    }
}

