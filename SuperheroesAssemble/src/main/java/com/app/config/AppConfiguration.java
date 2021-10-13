package com.app.config;

import com.app.controllers.InputsController;
import org.springframework.context.annotation.Bean;
import com.app.controllers.HomeController;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration
{
    @Bean
    public HomeController getHomeControllerBean() {
        return new HomeController();
    }
    
    @Bean
    public InputsController getInputControllerBean() {
        return new InputsController();
    }
}
