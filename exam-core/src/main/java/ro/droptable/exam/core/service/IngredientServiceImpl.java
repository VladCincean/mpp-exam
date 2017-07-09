package ro.droptable.exam.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.droptable.exam.core.model.Ingredient;
import ro.droptable.exam.core.repository.IngredientRepository;

import java.util.List;

/**
 * Created by vlad on 20/06/2017.
 */
@Service
public class IngredientServiceImpl implements IngredientService {
    private static final Logger log = LoggerFactory.getLogger(IngredientServiceImpl.class);

    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    @Transactional
    public List<Ingredient> findAll() {
        log.trace("findAll");

        List<Ingredient> ingredients = ingredientRepository.findAllEntityGraph();

        log.trace("findAll: ingredients = {}", ingredients);

        return ingredients;
    }

    @Override
    @Transactional
    public Ingredient findIngredient(Long ingredientId) {
        log.trace("findIngredient: ingredientId = {}", ingredientId);

        Ingredient ingredient = ingredientRepository.findOneEntityGraph(ingredientId);

        log.trace("findIngredient: ingredient = {}", ingredient);

        return ingredient;
    }
}
