package ro.droptable.exam.core.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import ro.droptable.exam.core.model.Ingredient;
import ro.droptable.exam.core.model.Ingredient;

import java.util.List;

/**
 * Created by vlad on 20/06/2017.
 */
public interface IngredientRepository extends ExamRepository<Ingredient, Long> {

    @Query("SELECT DISTINCT p FROM Ingredient p")
    @EntityGraph(value = "ingredientWithPizza", type = EntityGraph.EntityGraphType.LOAD)
    List<Ingredient> findAllEntityGraph();

    @Query("SELECT DISTINCT p FROM Ingredient p WHERE p.id = ?1")
    @EntityGraph(value = "ingredientWithPizza", type = EntityGraph.EntityGraphType.LOAD)
    Ingredient findOneEntityGraph(Long ingredientId);
}
