package preproject.model;

public class IncomeResponse {
    private Long id;
    private double income;

    public IncomeResponse() {

    }

    public IncomeResponse(long id, double income) {
        this.id = id;
        this.income = income;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

}
