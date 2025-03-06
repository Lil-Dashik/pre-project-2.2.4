package preproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IncomeService {
    private final RestTemplate restTemplate;

    @Autowired
    public IncomeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
