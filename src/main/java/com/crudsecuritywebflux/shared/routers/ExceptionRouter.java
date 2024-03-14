package com.crudsecuritywebflux.shared.routers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ExceptionRouter {

    @Bean
    public WebProperties.Resources resources() {
        return new WebProperties.Resources();
    }
}
