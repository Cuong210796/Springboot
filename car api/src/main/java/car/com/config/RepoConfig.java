package car.com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import car.com.repository.CarDao;

@Configuration
public class RepoConfig {
    @Bean
    public CarDao carDao() {
        return new CarDao("car.csv");
    }
}
