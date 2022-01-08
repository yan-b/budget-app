package htw.berlin.budgetapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BudgetAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(BudgetAppApplication.class, args);
	}
}