package finalproject.petable.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class RestConfig {

    @Bean
    public RestClient restClient(){
        return RestClient
                .builder()
                .baseUrl("http://localhost:8081")
                .defaultHeader("Content-Type", MediaType.MULTIPART_FORM_DATA_VALUE)
                .build();
    }
}
