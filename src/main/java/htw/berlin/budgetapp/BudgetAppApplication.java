package htw.berlin.budgetapp;

import htw.berlin.budgetapp.presistence.AccountEntity;
import htw.berlin.budgetapp.presistence.AccountRepository;
import htw.berlin.budgetapp.service.AccountType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
public class BudgetAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetAppApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}