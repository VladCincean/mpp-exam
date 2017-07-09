package ro.droptable.exam.web.dto;

import lombok.*;

/**
 * Created by vlad on 20/06/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class IngredientDto extends BaseDto {
    private String name;
    private Long pizzaId;
}
