package com.yahya.task.tracker.tasktracker.config;

import com.yahya.task.tracker.tasktracker.security.jwt.JwtConfig;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration {

    private final JwtConfig jwtConfig;

    public WebConfiguration(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
//              TODO Learn and Enable CORS

                String exposedHeader = jwtConfig.getAuthorizationHeader();
                System.out.println("\n\n"+  exposedHeader +"\n\n");
                registry.addMapping("/api/**")
                        .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
                        .exposedHeaders(exposedHeader, exposedHeader.toLowerCase());
                WebMvcConfigurer.super.addCorsMappings(registry);
            }
        };
    }



}
