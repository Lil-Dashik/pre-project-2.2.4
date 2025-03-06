package preproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import preproject.model.IncomeResponse;

@Service
public class IncomeService {
    private final RestTemplate restTemplate;

    @Autowired
    public IncomeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
