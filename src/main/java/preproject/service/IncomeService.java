package preproject.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import starter.client.IncomeClient;
import starter.client.IncomeResponse;


import java.util.Arrays;
import java.util.Objects;

@Service
public class IncomeService {
    private final IncomeClient incomeClient;

    public IncomeService(IncomeClient incomeClient) {
        this.incomeClient = incomeClient;
    }

    public IncomeResponse[] getIncomes() {
        return incomeClient.fetchIncomes();
    }

    public Double getUserIncome(Long userId) {
        return Arrays.stream(incomeClient.fetchIncomes())
                .filter(i -> Objects.equals(i.getId(), userId))
                .map(IncomeResponse::getIncome)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The user with the ID " + userId + " was not found!"));

    }
}

