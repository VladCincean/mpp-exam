import { Component, OnInit } from '@angular/core';
import {Pizza} from "../shared/pizza.model";
import {PizzaService} from "../shared/pizza.service";
import {Router} from "@angular/router";

@Component({
  moduleId: module.id,
  selector: 'app-pizza-list',
  templateUrl: './pizza-list.component.html',
  styleUrls: ['./pizza-list.component.css']
})
export class PizzaListComponent implements OnInit {
  errorMessage: string;
  pizzas: Pizza[];
  selectedPizza: Pizza;
  priceAll: number;

  constructor(
    private pizzaService: PizzaService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getPizzas()
  }

  private getPizzas() {
    this.pizzaService.getPizzas()
      .subscribe(
        pizzas => this.pizzas = pizzas,
        error => this.errorMessage = <any>error
      );
  }

  // onSelect(pizza: Pizza): void {
  //   this.selectedPizza = pizza;
  // }

  updateOne(pizza: Pizza) {
    console.log("update one pizza: " + pizza);
    this.pizzaService.update(pizza)
      .subscribe(_ => {
        console.log("pizza updated successfully");
      });
  }

  updateAll() {
    console.log("Update prices with " + this.priceAll);
    this.pizzaService.updateAll(this.priceAll)
      .subscribe(pizzas => {
        this.pizzas = pizzas;
      })
  }

  gotoIngredients(pizza: Pizza): void {
    this.router.navigate([
      '/pizzashop2010/pizza', pizza.id
    ]);
  }
}
