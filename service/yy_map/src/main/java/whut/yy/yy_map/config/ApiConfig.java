package whut.yy.yy_map.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "yy-map")
@Getter
@Setter
public class ApiConfig {
    private String output;
    private String ak;
}
