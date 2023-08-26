package com.starking.crud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsUtils;

import com.starking.crud.services.JwtService;
import com.starking.crud.services.SecurityUserDetailsService;

import lombok.RequiredArgsConstructor;


/**
 * @author pedroRhamon
 */
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
	
	private final JwtService jwtService;
	
	private final SecurityUserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	@Bean
	public JwtTokenFilter jwtTokenFilter() {
		return new JwtTokenFilter(jwtService, userDetailsService);
	}
	
	@Bean
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
	}
	
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
