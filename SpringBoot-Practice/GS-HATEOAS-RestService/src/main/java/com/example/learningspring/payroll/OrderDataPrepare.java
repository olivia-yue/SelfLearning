package com.example.learningspring.payroll;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class OrderDataPrepare {

	@Bean
	CommandLineRunner dataPrepare(OrderRepository repository) {
		return args -> {
			repository.save(new Order("Mac Book", Status.COMPLETED));
			repository.save(new Order("Iphone", Status.IN_PROGRESS));
			
			repository.findAll().forEach(order -> {
				log.info("Preloaded " + order);
			});
		};
		
	}
}
