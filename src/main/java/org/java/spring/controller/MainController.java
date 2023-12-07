package org.java.spring.controller;

import java.util.List;

import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class MainController {

	@Autowired
	private PizzaService pizzaService;

	@GetMapping()
	public String searchPizzas(Model model, @RequestParam(required = false) String q) {

		List<Pizza> pizzas = q == null ? pizzaService.findAll() : pizzaService.findByTitle(q);

		model.addAttribute("pizzas", pizzas);
		model.addAttribute("q", q == null ? "" : q);

		return "Pizzas";
	}

	@GetMapping("/pizzas/create")
	public String createPizza(Model model) {

		Pizza pizza = new Pizza();
		model.addAttribute("pizza", pizza);
		return "pizza-form";
	}
	@PostMapping("/pizzas/create")
	public String storePizza(Model model, @Valid @ModelAttribute Pizza pizza, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("pizza", pizza);
			return "pizza-form";
		}
		pizzaService.save(pizza);
		return "redirect:/";

	}

	@GetMapping("/pizzas/{id}")
	public String getPizzaId(Model model, @PathVariable int id) {
		Pizza pizzaId = pizzaService.findById(id);
		model.addAttribute("pizzaId", pizzaId);
		return "pizza-detail";

	}
	
	@GetMapping("/pizzas/edit/{id}")
	public String editPizza(Model model,@PathVariable int id) {
		Pizza pizza = pizzaService.findById(id);
		model.addAttribute("pizza", pizza);
		return"pizza-form";
	}
	@PostMapping("/pizzas/edit/{id}")
	public String updatePizza(Model model,@Valid @ModelAttribute Pizza pizza, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("pizza", pizza);
			return "pizza-form";
		}
		pizzaService.save(pizza);
		return "redirect:/";
	}
	
	@PostMapping("/pizzas/delete/{id}")
	public String deletePizza(@PathVariable int id) {
		Pizza pizza=pizzaService.findById(id);
		pizzaService.delete(pizza);
		return "redirect:/";
	}
}
