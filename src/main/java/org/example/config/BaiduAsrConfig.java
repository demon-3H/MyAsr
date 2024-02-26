package org.example.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:baiduasr.properties")
@Data
public class BaiduAsrConfig {
    @Value("${apiKey}")
    private String apiKey;
    @Value("${apiSecret}")
    private String apiSecret;

}
