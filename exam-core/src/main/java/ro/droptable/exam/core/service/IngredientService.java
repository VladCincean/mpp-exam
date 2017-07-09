package ro.droptable.exam.core.service;

import ro.droptable.exam.core.model.Ingredient;

import java.util.List;

/**
 * Created by vlad on 20/06/2017.
 */
public interface IngredientService {

    List<Ingredient> findAll();

    Ingredient findIngredient(Long ingredientId);
}
