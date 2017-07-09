package ro.droptable.exam.web.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.droptable.exam.core.model.Ingredient;
import ro.droptable.exam.core.model.Pizza;
import ro.droptable.exam.web.dto.IngredientDto;

import java.util.stream.Collectors;

/**
 * Created by vlad on 20/06/2017.
 */
@Component
public class IngredientConverter extends BaseConverter<Ingredient, IngredientDto> {
    private static final Logger log = LoggerFactory.getLogger(IngredientConverter.class);

    @Override
    public IngredientDto convertModelToDto(Ingredient ingredient) {
        log.trace("convertModelToDto: ingredient = {}", ingredient);

        IngredientDto ingredientDto = IngredientDto.builder()
                .name(ingredient.getName())
                .pizzaId(ingredient.getPizza().getId())
                .build();
        ingredientDto.setId(ingredient.getId());

        log.trace("convertModelToDto: ingredientDto = {]", ingredientDto);

        return ingredientDto;
    }

    @Override
    public Ingredient convertDtoToModel(IngredientDto ingredientDto) {
        log.trace("convertDtoToModel: ingredientDto = {}", ingredientDto);

        Ingredient ingredient = Ingredient.builder()
                .name(ingredientDto.getName())
                .pizza(null)
                .build();
        ingredient.setId(ingredientDto.getId());

        log.trace("convertDtoToModel: ingredient = {]", ingredient);

        return ingredient;
    }
}
