package finalproject.petable.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppBeanConfig {

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
