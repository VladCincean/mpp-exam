package ro.droptable.exam.web.dto;

import lombok.*;

import java.util.Set;

/**
 * Created by vlad on 20/06/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PizzaDto extends BaseDto {
    private String name;
    private String description;
    private float price;
    private Set<IngredientDto> ingredients;
}
