package com.starking.crud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author pedroRhamon
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

}
