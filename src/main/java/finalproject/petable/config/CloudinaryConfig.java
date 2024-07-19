package finalproject.petable.config;

import com.cloudinary.Cloudinary;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "cloudinary.api")
public class CloudinaryConfig {

    private String cloudName;
    private String api_key;
    private String api_secret;

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary;
        Map valuesMap = new HashMap<>();
        valuesMap.put("cloud_name", cloudName);
        valuesMap.put("api_key", api_key);
        valuesMap.put("api_secret", api_secret);
        valuesMap.put("secure", true);
        cloudinary = new Cloudinary(valuesMap);
        return cloudinary;
    }

    public String getCloudName() {
        return cloudName;
    }

    public void setCloudName(String cloudName) {
        this.cloudName = cloudName;
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
