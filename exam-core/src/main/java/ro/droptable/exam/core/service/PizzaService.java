package ro.droptable.exam.core.service;

import ro.droptable.exam.core.model.Pizza;

import java.util.List;

/**
 * Created by vlad on 20/06/2017.
 */
public interface PizzaService {

    List<Pizza> findAll();

    Pizza findPizza(Long pizzaId);

    Pizza updatePizza(Long pizzaId, String name, Float price);

    void updateAllPizza(Float dif);

    void deletePizza(Long pizzaId);
}
