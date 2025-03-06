package preproject.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    private int maxCar;
    private SortConfig sort;

    public int getMaxCar() {
        return maxCar;
    }

    public void setMaxCar(int maxCar) {
        this.maxCar = maxCar;
    }

    public SortConfig getSort() {
        return sort;
    }

    public void setSort(SortConfig sort) {
        this.sort = sort;
    }
}