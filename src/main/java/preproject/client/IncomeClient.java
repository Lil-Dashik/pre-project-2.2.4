package preproject.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import preproject.config.UrlConfig;
import preproject.model.IncomeResponse;

@RequiredArgsConstructor
@Component
public class IncomeClient {
    private final RestTemplate restTemplate;
    private final UrlConfig urlConfig;

    public IncomeResponse[] fetchIncomes() {
        try {
            String url = urlConfig.getIncomes();
            ResponseEntity<IncomeResponse[]> response = restTemplate.getForEntity(url, IncomeResponse[].class);
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
