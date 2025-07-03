package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // Aplica a todas las rutas
                        .allowedOrigins("http://localhost:3002")  // Permite solicitudes desde este origen
                        .allowedMethods("GET", "POST", "PUT", "DELETE")  // Métodos HTTP permitidos
                        .allowedHeaders("*");  // Permite todos los encabezados

            }
        };
    }
}
