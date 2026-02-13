package com.medicare.backend;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
public class MediCareBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediCareBackendApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		// Configuration to handle nested mapping or different field names
		modelMapper.getConfiguration()
				.setFieldMatchingEnabled(true)
				.setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

		return modelMapper;
	}

}
