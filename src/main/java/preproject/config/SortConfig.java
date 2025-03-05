package preproject.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "sort")
public class SortConfig {
    private List<String> enabledFields;
    public List<String> getEnabledFields() {
        return enabledFields;
    }
    public void setEnabledFields(List<String> enabledFields) {
        this.enabledFields = enabledFields;
    }
}
