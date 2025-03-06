package preproject.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "loan")
public class LoanConfig {
    private double minimalIncome;
    private double maxCreditRateFromIncome;
    private double minCarPriceForLoan;
    private double maxCreditRateFromCarPrice;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
