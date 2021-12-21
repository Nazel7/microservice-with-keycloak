package com.microservices.orderservice.configs;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@Configuration
public class RequestInterceptorConfig {

    @Bean
    public RequestInterceptor requestInterceptor(){

        return requestTemplate ->  {

               JwtAuthenticationToken authenticationToken =
                       (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
               requestTemplate.header("Authorization", "Bearer "
                       + authenticationToken.getToken().getTokenValue());
            };
    }
}
