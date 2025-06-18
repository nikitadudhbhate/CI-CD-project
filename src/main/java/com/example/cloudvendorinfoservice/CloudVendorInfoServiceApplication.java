package com.example.cloudvendorinfoservice;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class CloudVendorInfoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudVendorInfoServiceApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI(
			@Value("${apiTitle}") String apiTitle,
			@Value("${apiDescription}") String apiDescription,
			@Value("${apiVersion}") String apiVersion,
			@Value("${apiContactName}") String apiContactName,
			@Value("${apiContactEmail}") String apiContactEmail,
			@Value("${apiContactUrl}") String apiContactUrl

	) {
		return new OpenAPI()
				.components(new Components())
				.info(new Info()
						.title(apiTitle)
						.description(apiDescription)
						.version(apiVersion)
						.contact(new Contact().name(apiContactName).email(apiContactEmail).url(apiContactUrl))
				);
	}

}
