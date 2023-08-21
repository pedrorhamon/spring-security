package com.starking.crud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsUtils;


/**
 * @author pedroRhamon
 */
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
            		.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
    				.requestMatchers(HttpMethod.GET, "/actuator/**").permitAll()
    				.requestMatchers(HttpMethod.POST, "/api/usuarios/autenticar").permitAll()
    				.requestMatchers(HttpMethod.POST, "/api/usuarios").permitAll()
                .anyRequest().authenticated()
            );
        return http.build();
    }

}
