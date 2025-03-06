package preproject.model;

public class UserIncome {
    private Long id;
    private double income;

    public UserIncome() {
    }

    public UserIncome(Long id, double income) {
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

    @Override
    public String toString() {
        return "UserIncome{" +
                "id=" + id +
                ", income=" + income +
                '}';
    }
}
