package com.ftn.PreporukaOdevneKombinacije.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //update: for some reason DELETE is only blocked, so I just added manually
        registry.addMapping("/**").allowedOrigins("https://localhost:4200")
                .allowedMethods("PUT", "POST", "DELETE", "GET", "OPTIONS");
    }
}
