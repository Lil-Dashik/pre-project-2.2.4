package preproject.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@ConfigurationProperties(prefix = "loan")
public class LoanConfig {
    private double minimalIncome;
    private double maxCreditRateFromIncome;
    private double minCarPriceForLoan;
    private double maxCreditRateFromCarPrice;

    public double getMinimalIncome() {
        return minimalIncome;
    }

    public void setMinimalIncome(double minimalIncome) {
        this.minimalIncome = minimalIncome;
    }

    public double getMaxCreditRateFromIncome() {
        return maxCreditRateFromIncome;
    }

    public void setMaxCreditRateFromIncome(double maxCreditRateFromIncome) {
        this.maxCreditRateFromIncome = maxCreditRateFromIncome;
    }

    public double getMinCarPriceForLoan() {
        return minCarPriceForLoan;
    }

    public void setMinCarPriceForLoan(double minCarPriceForLoan) {
        this.minCarPriceForLoan = minCarPriceForLoan;
    }

    public double getMaxCreditRateFromCarPrice() {
        return maxCreditRateFromCarPrice;
    }

    public void setMaxCreditRateFromCarPrice(double maxCreditRateFromCarPrice) {
        this.maxCreditRateFromCarPrice = maxCreditRateFromCarPrice;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
