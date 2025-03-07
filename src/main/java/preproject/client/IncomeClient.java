package preproject.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
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
            String apiUrl = urlConfig.getIncomes();
            ResponseEntity<IncomeResponse[]> response = restTemplate.getForEntity(apiUrl, IncomeResponse[].class);
            if (response.getStatusCode().isError()) {
                throw new IllegalStateException("Error: The API returned the status " + response.getStatusCode());
            }
            IncomeResponse[] incomes = response.getBody();
            if (incomes == null) {
                throw new IllegalStateException("Error: The API returned null instead of an array of revenue!");
            }
            return incomes;
        } catch (RestClientException e) {
            System.out.println(" Network error: " + e.getMessage());
            throw e;
        }
    }
}
