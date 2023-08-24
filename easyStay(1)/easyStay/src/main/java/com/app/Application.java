package com.app;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
//import org.modelmapper.ModelMapper;
//import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean // equivalent to <bean id ..../> in xml file This annotation is used in a configuration class to indicate that a method produces a bean that should be managed by the Spring container. In this case, the method is named mapper.
	public ModelMapper mapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}

}
