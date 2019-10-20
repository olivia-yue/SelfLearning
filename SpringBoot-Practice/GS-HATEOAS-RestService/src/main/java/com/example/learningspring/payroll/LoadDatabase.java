package com.example.learningspring.payroll;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
public class LoadDatabase {
	
	//private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
	@Bean
	CommandLineRunner initDatabase(EmployeeRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(new Employee("Bilbo", "Baggins", "tester")));
			log.info("Preloading " + repository.save(new Employee("Frodo", "Baggins", "developer")));
		};
	}

}
