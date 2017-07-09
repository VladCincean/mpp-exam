package ro.droptable.exam.web.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.droptable.exam.core.model.Ingredient;
import ro.droptable.exam.core.model.Pizza;
import ro.droptable.exam.web.dto.IngredientDto;
import ro.droptable.exam.web.dto.PizzaDto;

import java.util.stream.Collectors;

/**
 * Created by vlad on 20/06/2017.
 */
@Component
public class PizzaConverter extends BaseConverter<Pizza, PizzaDto> {
    private static final Logger log = LoggerFactory.getLogger(PizzaConverter.class);

    @Override
    public PizzaDto convertModelToDto(Pizza pizza) {
        log.trace("convertModelToDto: pizza = {}", pizza);

        PizzaDto pizzaDto = PizzaDto.builder()
                .name(pizza.getName())
                .description(pizza.getDescription())
                .price(pizza.getPrice())
                .build();
        pizzaDto.setId(pizza.getId());
        pizzaDto.setIngredients(
                pizza.getIngredients().stream()
                .map(i -> IngredientDto.builder()
                    .name(i.getName())
                    .build()
                ).collect(Collectors.toSet())
        );

        log.trace("convertModelToDto: pizzaDto = {}", pizzaDto);

        return pizzaDto;
    }

    @Override
    public Pizza convertDtoToModel(PizzaDto pizzaDto) {
        log.trace("convertDtoToModel: pizzaDto = {}", pizzaDto);

        Pizza pizza = Pizza.builder()
                .name(pizzaDto.getName())
                .description(pizzaDto.getDescription())
                .price(pizzaDto.getPrice())
                .ingredients(null)
                .build();
        pizza.setId(pizzaDto.getId());

        log.trace("convertDtoToModel: pizza = {]", pizza);

        return pizza;
    }
}
