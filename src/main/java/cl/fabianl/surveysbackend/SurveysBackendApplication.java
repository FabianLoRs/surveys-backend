package cl.fabianl.surveysbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import cl.fabianl.surveysbackend.services.AppProperties;

@SpringBootApplication
public class SurveysBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SurveysBackendApplication.class, args);
		System.out.println("ARRIBA!");
	}

	@Bean
	public BCryptPasswordEncoder BCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}

	@Bean(name = "AppProperties")
	public AppProperties getAppProperties() {
		return new AppProperties();
	}

}
