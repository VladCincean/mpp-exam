package ro.droptable.exam.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * Created by vlad on 20/06/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IngredientsDto {
    private Set<IngredientDto> ingredients;
}
