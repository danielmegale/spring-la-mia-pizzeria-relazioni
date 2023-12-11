package org.java.spring.controller;

import org.java.spring.db.pojo.Ingredient;
import org.java.spring.db.service.IngredientService;
import org.java.spring.db.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@Controller
public class IngredientController {

	@Autowired
	private PizzaService pizzaService;
	
    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    public String listIngredients(Model model) {
        List<Ingredient> ingredients = ingredientService.findAll();
        model.addAttribute("ingredients", ingredients);
        return "ingredient-list";
    }

    @GetMapping("/create")
    public String createIngredientForm(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        model.addAttribute("actionUrl", "/ingredients/create");
        return "ingredient-form";
    }


    @PostMapping("/create")
    public String createIngredient(@Valid @ModelAttribute Ingredient ingredient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ingredient-form";
        }
        ingredientService.save(ingredient);
        return "redirect:/ingredients";
    }

    @GetMapping("/edit/{id}")
    public String editIngredientForm(@PathVariable int id, Model model) {
        Ingredient ingredient = ingredientService.findById(id);
        if (ingredient != null) {
            model.addAttribute("ingredient", ingredient);
            model.addAttribute("actionUrl", "/ingredients/edit/" + id);
            return "ingredient-form";
        } else {

            return "redirect:/ingredients";
        }
    }


    @PostMapping("/edit/{id}")
    public String editIngredient(@PathVariable Long id, @Valid @ModelAttribute Ingredient ingredient,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ingredient-form";
        }
        ingredient.setId(id);
        ingredientService.save(ingredient);
        return "redirect:/ingredients";
    }


}
