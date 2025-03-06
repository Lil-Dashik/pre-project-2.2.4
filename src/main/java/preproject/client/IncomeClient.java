package preproject.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import preproject.config.UrlConfig;
import preproject.model.IncomeResponse;

@Component
public class IncomeClient {
    private final RestTemplate restTemplate;
    private final String apiUrl;

    public IncomeClient(RestTemplate restTemplate, UrlConfig urlConfig) {
        this.restTemplate = restTemplate;
        this.apiUrl = urlConfig.getIncomes();
    }

    public IncomeResponse[] fetchIncomes() {
        try {
            ResponseEntity<IncomeResponse[]> response = restTemplate.getForEntity(apiUrl, IncomeResponse[].class);
            IncomeResponse[] incomes = response.getBody();
            if (incomes == null || incomes.length == 0) {
                return null;
            }
            return incomes;
        } catch (Exception e) {
            System.out.println("Error when receiving income:" + e.getMessage());
            return new IncomeResponse[0];
        }
    }
}
