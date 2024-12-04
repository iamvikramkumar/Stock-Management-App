package com.payment.app.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
@Configuration
public class SwaggerConfig  {
	@Bean
	public OpenAPI customOpenAPI() {
		        return new OpenAPI()
		                .info(new Info()
		                .title("Report controller")
		                .version("1.0")
		                .description("API documentation for managing user"));
	}

}