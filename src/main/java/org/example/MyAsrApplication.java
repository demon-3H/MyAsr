package org.example;

import org.example.config.BaiduAsrConfig;
import org.example.utils.BaiduAsrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sun.tools.jar.CommandLine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

@SpringBootApplication
public class MyAsrApplication {
    @Autowired
    private BaiduAsrConfig baiduAsrConfig;

    public static void main(String[] args) {
        SpringApplication.run(MyAsrApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(){
        return args -> {
            BaiduAsrUtil.init(baiduAsrConfig.getApiKey(),baiduAsrConfig.getApiSecret());
        };
    }
}
