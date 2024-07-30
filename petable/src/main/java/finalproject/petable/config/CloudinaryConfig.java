package finalproject.petable.config;

import com.cloudinary.Cloudinary;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "cloudinary.api")
public class CloudinaryConfig {
    private String cloud_name;
    private String api_key;
    private String api_secret;
    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", cloud_name);
        config.put("api_key", api_key);
        config.put("api_secret", api_secret);

        return new Cloudinary(config);
    }

    public String getCloud_name() {
        return cloud_name;
    }

    public void setCloud_name(String cloud_name) {
        this.cloud_name = cloud_name;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getApi_secret() {
        return api_secret;
    }

    public void setApi_secret(String api_secret) {
        this.api_secret = api_secret;
    }
}
