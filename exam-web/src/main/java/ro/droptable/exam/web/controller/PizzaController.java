package ro.droptable.exam.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.droptable.exam.core.model.Ingredient;
import ro.droptable.exam.core.model.Pizza;
import ro.droptable.exam.core.service.PizzaService;
import ro.droptable.exam.web.converter.IngredientConverter;
import ro.droptable.exam.web.converter.PizzaConverter;
import ro.droptable.exam.web.dto.EmptyJsonResponse;
import ro.droptable.exam.web.dto.IngredientsDto;
import ro.droptable.exam.web.dto.PizzaDto;
import ro.droptable.exam.web.dto.PizzasDto;

import java.util.*;

/**
 * Created by vlad on 20/06/2017.
 */
@RestController
public class PizzaController {
    private static final Logger log = LoggerFactory.getLogger(PizzaController.class);

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private PizzaConverter pizzaConverter;

    @Autowired
    private IngredientConverter ingredientConverter;

    @RequestMapping(value = "/pizzas", method = RequestMethod.GET)
    public PizzasDto getPizzas() {
        log.trace("getPizzas -- method entry");

        List<Pizza> pizzas = pizzaService.findAll();

        log.trace("getPizzas: pizzas = {}", pizzas);

        return new PizzasDto(pizzaConverter.convertModelsToDtos(pizzas));
    }

    @RequestMapping(value = "/pizzas/ingredients/{pizzaId}", method = RequestMethod.GET)
    public IngredientsDto getPizzaIngredients(
            @PathVariable final Long pizzaId
    ) {
        log.trace("getPizzaIngredients: pizzaId = {}", pizzaId);

        Set<Ingredient> ingredients = pizzaService.findPizza(pizzaId).getIngredients();

        log.trace("getPizzaIngredients: ingredients = {}", ingredients);

        return new IngredientsDto(ingredientConverter.convertModelsToDtos(ingredients));
    }

    @RequestMapping(value = "/pizzas/{pizzaId}", method = RequestMethod.PUT)
    public Map<String, PizzaDto> updatePizza(
            @PathVariable final Long pizzaId,
            @RequestBody final Map<String, PizzaDto> pizzaDtoMap
    ) {
        log.trace("updatePizza: pizzaId = {}, pizzaDtoMap = {}", pizzaId, pizzaDtoMap);

        PizzaDto pizzaDto = pizzaDtoMap.get("pizza");
        Pizza pizza = pizzaService.updatePizza(
                pizzaId,
                pizzaDto.getName(),
                pizzaDto.getPrice()
        );

        Map<String, PizzaDto> result = new HashMap<>();
        result.put("pizza", pizzaConverter.convertModelToDto(pizza));

        log.trace("updatePizza: result = {}", result);

        return result;
    }

    @RequestMapping(value = "pizzas/updateAll", method = RequestMethod.PUT)
    public PizzasDto updateAllPizzas(
            @RequestBody Map<String, Float> priceDtoMap
    ) {
        log.trace("updateAllPizzas: priceDtoMap = {}", priceDtoMap);

        Float price = priceDtoMap.get("price");

        pizzaService.updateAllPizza(price);
        List<Pizza> pizzas = pizzaService.findAll();

        log.trace("updateAllPizzas: pizzas = {}", pizzas);

        return new PizzasDto(pizzaConverter.convertModelsToDtos(pizzas));
    }

    @RequestMapping(value = "pizzas/{pizzaId}", method = RequestMethod.DELETE)
    public ResponseEntity deletePizza(
            @PathVariable final Long pizzaId
    ) {
        log.trace("deletePizza: pizzaId={}", pizzaId);

        pizzaService.deletePizza(pizzaId);

        log.trace("deletePizza - method end");

        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }

    @RequestMapping(value = "pizzas/deleteSelected", method = RequestMethod.PUT)
    public PizzasDto deleteSelected(
            @RequestBody final Map<String, PizzaDto[]> pizzasDtoMap
    ) {
        log.trace("deleteSelected: pizzasDtoMap = {}", pizzasDtoMap);

        PizzaDto[] pizzasDto = pizzasDtoMap.get("pizzas");
        log.trace("deleteSelected: pizzasDto.length = {}", pizzasDto.length);

        Set<PizzaDto> dtos = new HashSet<>(Arrays.asList(pizzasDto));
        log.trace("deleteSelected: dtos = {}", dtos);

        Set<Long> idsToDelete = this.pizzaConverter.convertDTOsToIDs(dtos);
        for (Long id : idsToDelete) {
            this.pizzaService.deletePizza(id);
        }

        List<Pizza> pizzas = pizzaService.findAll();

        log.trace("deleteSelected: pizzas = {}", pizzas);

        return new PizzasDto(pizzaConverter.convertModelsToDtos(pizzas));
    }

    // TODO: DONE: backup
//    @RequestMapping(value = "pizzas/deleteSelected", method = RequestMethod.PUT)
//    public PizzasDto deleteSelected(
//            @RequestBody final Map<String, PizzasDto> pizzasDtoMap
//    ) {
//        log.trace("deleteSelected: pizzasDtoMap = {}", pizzasDtoMap);
//
//        PizzasDto pizzasDto = pizzasDtoMap.get("pizzas");
//        log.trace("deleteSelected: pizzasDto = {}", pizzasDto);
//
//        Set<Long> idsToDelete = this.pizzaConverter.convertDTOsToIDs(pizzasDto.getPizzas());
//        for (Long id : idsToDelete) {
//            this.pizzaService.deletePizza(id);
//        }
//
//        List<Pizza> pizzas = pizzaService.findAll();
//
//        log.trace("deleteSelected: pizzas = {}", pizzas);
//
//        return new PizzasDto(pizzaConverter.convertModelsToDtos(pizzas));
//    }
}
