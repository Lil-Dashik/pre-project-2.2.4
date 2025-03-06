package preproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import preproject.model.IncomeResponse;

@Service
public class IncomeService {
    private final RestTemplate restTemplate;
    private final String incomesURL = "https://66055cd12ca9478ea1801f2e.mockapi.io/api/users/income";

    @Autowired
    public IncomeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public double getUserIncome(Long userId) {
        try {
            ResponseEntity<IncomeResponse> response = restTemplate.getForEntity(incomesURL + "/" + userId, IncomeResponse.class);
            System.out.println("Response Status: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody());
            return response.getBody() != null ? response.getBody().getIncome() : 0.0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }
}
