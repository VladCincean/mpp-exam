package ro.droptable.exam.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.droptable.exam.core.model.Ingredient;
import ro.droptable.exam.core.service.IngredientService;
import ro.droptable.exam.web.converter.IngredientConverter;
import ro.droptable.exam.web.dto.IngredientsDto;

import java.util.List;

/**
 * Created by vlad on 20/06/2017.
 */
@RestController
public class IngredientController {
    private static final Logger log = LoggerFactory.getLogger(IngredientController.class);

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private IngredientConverter ingredientConverter;

    @RequestMapping(value = "/ingredients", method = RequestMethod.GET)
    public IngredientsDto getIngredients() {
        log.trace("getIngredients -- method entry");

        List<Ingredient> ingredients = ingredientService.findAll();

        log.trace("getIngredients: ingredients = {}", ingredients);

        return new IngredientsDto(ingredientConverter.convertModelsToDtos(ingredients));
    }
}
