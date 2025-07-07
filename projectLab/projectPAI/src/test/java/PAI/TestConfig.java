package PAI;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("PAI")
@EntityScan("PAI.persistence.datamodel")
@EnableJpaRepositories("PAI.persistence.springdata")
public class TestConfig {
}