package ro.droptable.exam.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.droptable.exam.core.model.Pizza;
import ro.droptable.exam.core.repository.IngredientRepository;
import ro.droptable.exam.core.repository.PizzaRepository;

import java.util.List;

/**
 * Created by vlad on 20/06/2017.
 */
@Service
public class PizzaServiceImpl implements PizzaService {
    private static final Logger log = LoggerFactory.getLogger(PizzaServiceImpl.class);

    @Autowired
    private PizzaRepository pizzaRepository;

//    @Autowired
//    private IngredientRepository ingredientRepository;

    @Override
    @Transactional
    public List<Pizza> findAll() {
        log.trace("findAll");

        List<Pizza> pizzas = pizzaRepository.findAllEntityGraph();

        log.trace("findAll: pizzas = {}", pizzas);

        return pizzas;
    }

    @Override
    @Transactional
    public Pizza findPizza(Long pizzaId) {
        log.trace("findPizza: pizzaId = {}", pizzaId);

        Pizza pizza = pizzaRepository.findOneEntityGraph(pizzaId);

        log.trace("findPizza: pizza = {}", pizza);

        return pizza;
    }

    @Override
    @Transactional
    public Pizza updatePizza(Long pizzaId, String name, Float price) {
        log.trace(
                "updatePizza: pizzaId = {}, name ={}, price = {}",
                name,
                pizzaId,
                price
        );

//        Pizza pizza = pizzaRepository.findOne(pizzaId);
        Pizza pizza = pizzaRepository.findOneEntityGraph(pizzaId);
        pizza.setName(name);
        pizza.setPrice(price);

        log.trace("updatePizza: pizza = {}", pizza);

        return pizza;
    }

    @Override
    @Transactional
    public void updateAllPizza(Float dif) {
        log.trace("updateAllPizza: dif = {}", dif);

        pizzaRepository.updateAllPrice(dif);

        log.trace("updateAllPizza -- method end");
    }
}
