package preproject.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;



@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "loan")
public class LoanConfig {
    private double minimalIncome;
    private double maxCreditRateFromIncome;
    private double minCarPriceForLoan;
    private double maxCreditRateFromCarPrice;
}
