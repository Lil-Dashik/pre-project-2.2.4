package preproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import preproject.service.LoanService;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/loan")
public class LoanController {
    @Autowired
    private LoanService loanService;

    @GetMapping
    public ResponseEntity<Map<String, Double>> getLoanApproval(@RequestParam Long userId) {
        double approvedLoan = loanService.getApprovedLoan(userId);
        Map<String, Double> response = new HashMap<>();
        response.put("approvedLoan", approvedLoan);
        return ResponseEntity.ok(response);
    }
}
