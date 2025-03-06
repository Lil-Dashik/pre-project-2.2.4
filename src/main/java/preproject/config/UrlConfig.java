package preproject.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "external.api")
public class UrlConfig {
    private String incomes;
    public String getIncomes() {
        return incomes;
    }
    public void setIncomes(String incomes) {
        this.incomes = incomes;
    }
    public UrlConfig() {

    }
    public UrlConfig(String incomes) {
        this.incomes = incomes;
    }
}
