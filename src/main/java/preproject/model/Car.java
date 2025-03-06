package preproject.model;

import javax.persistence.*;


@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String make;
    private String model;
    private int series;
    private double price;


    public Car() {
    }

    public Car(String make, String model, int series, double price) {
        this.make = make;
        this.model = model;
        this.series = series;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car [id=" + id + ", make=" + make + ", model=" + model + ", series=" + series + ", price=" + price + "]";
    }

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
