package preproject.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import preproject.config.LoanConfig;
import preproject.dao.UserRepository;
import preproject.model.Car;
import preproject.model.User;

@Service
public class LoanService {
    private final UserRepository userRepository;
    private final LoanConfig loanConfig;
    private final IncomeService incomeService;

    @Autowired
    public LoanService(UserRepository userRepository, LoanConfig loanConfig, IncomeService incomeService) {
        this.userRepository = userRepository;
        this.loanConfig = loanConfig;
        this.incomeService = incomeService;
    }

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

