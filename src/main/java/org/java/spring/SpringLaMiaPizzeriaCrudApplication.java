package org.java.spring;

import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {
	
	@Autowired
	private PizzaService pizzaService;
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}
	@Override
	public void run(String...args) throws Exception{
		pizzaService.save(new Pizza("Margherita", "Pizza con modoro e mozzarella", 4.99, null));
		pizzaService.save(new Pizza("Patatosa", "Pizza con modoro, mozzarella e patatine fritte", 6.99, null));
		pizzaService.save(new Pizza("4 Formaggi", "Pizza con mozzarella, Certosino, Gorgonzola D.O.P. Gim e parmigiano grattugiato,", 6.99, null));
		pizzaService.save(new Pizza("Prosciutto cotto", "Pizza con modoro, mozzarella e prosciutto cotto", 6.99, null));
	}
}
