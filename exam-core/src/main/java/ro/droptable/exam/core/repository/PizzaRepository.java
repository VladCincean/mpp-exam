package ro.droptable.exam.core.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ro.droptable.exam.core.model.Pizza;

import java.util.List;

/**
 * Created by vlad on 20/06/2017.
 */
public interface PizzaRepository extends ExamRepository<Pizza, Long> {

    @Query("SELECT DISTINCT p FROM Pizza p")
    @EntityGraph(value = "pizzaWithIngredients", type = EntityGraph.EntityGraphType.LOAD)
    List<Pizza> findAllEntityGraph();

    @Query("SELECT DISTINCT p FROM Pizza p WHERE p.id = ?1")
    @EntityGraph(value = "pizzaWithIngredients", type = EntityGraph.EntityGraphType.LOAD)
    Pizza findOneEntityGraph(Long pizzaId);

    @Query("UPDATE Pizza p SET p.price = p.price + ?1")
    @Modifying
    @Transactional
    void updateAllPrice(Float price);
}
