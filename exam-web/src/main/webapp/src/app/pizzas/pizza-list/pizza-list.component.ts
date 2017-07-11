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
  pizzas: Pizza[] = [];
  checkedPizzas: Pizza[] = [];
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

  isChecked(pizza: Pizza): boolean {
    let i: number = 0;
    for (i = 0; i < this.checkedPizzas.length; i++) {
      if (this.checkedPizzas[i].id === pizza.id) {
        return true;
      }
    }
    return false;
  }

  private hasIngredients(pizza: Pizza): boolean {
    if (pizza.ingredients.length === 0) {
      return false;
    }
    return true;
  }

  handleCheckboxChangeEvent(pizza: Pizza): void {
    if (this.isChecked(pizza)) {
      let i: number = 0;
      let index: number;
      for (i = 0; i < this.checkedPizzas.length; i++) {
        if (this.checkedPizzas[i].id === pizza.id) {
          index = i;
          break;
        }
      }
      this.checkedPizzas[index] = this.checkedPizzas[this.checkedPizzas.length - 1];
      this.checkedPizzas.pop();
    } else {
      this.checkedPizzas.push(pizza);
    }

    console.log('checkedPizzas: ', this.checkedPizzas);
  }

  handleDeleteSelectedClick(): void {
    console.log("delete selected button clicked");

    if (confirm("all selected pizzas will be deleted; do you want to proceed?")) {
      console.log('Dialog OK button selected');
      this.pizzaService.deleteSelected(this.checkedPizzas)
        .subscribe(pizzas => {
          this.pizzas = pizzas;
          this.checkedPizzas = [];
        });
    } else {
      console.log('Dialog Cancel button selected')
    }

    console.log("delete selected handler finished");
  }

  handleDeleteStepByStepClick(): void {
    console.log("delete step by step button clicked");

    // construct pizzas without ingredients array
    let pizzasWithoutIngredients: Pizza[] = [];
    let i: number = 0;
    for (i = 0; i < this.pizzas.length; i++) {
      if (!this.hasIngredients(this.pizzas[i])) {
        pizzasWithoutIngredients.push(this.pizzas[i]);
      }
    }

    // delete them
    console.log("delete step by step: deleting all pizzas without ingredients");
    this.pizzaService.deleteSelected(pizzasWithoutIngredients)
      .subscribe(pizzas => {
        this.pizzas = pizzas;
        console.log("delete step by step: all pizzas without ingredients - DELETED");

        // handling pizzas with ingredients
        //    1. copy them
        let pizzasCopy: Pizza[] = [];
        this.pizzas.forEach((p) => {
          pizzasCopy.push(Object.assign({}, p));
        });

        //
        // let j: number = 0;
        // for (j = 0; j < this.pizzas.length; j++) {
        //   pizzasCopy.push(this.pizzas[i]);
        // }

        console.log('this.pizzas: ', this.pizzas);
        console.log('pizzasCopy: ', pizzasCopy);

        //    2. handle each
        let myP: Pizza;
        let j: number = 0;
        for (j = 0; j < pizzasCopy.length; j++) {
          myP = pizzasCopy[j];
          if (confirm("pizza " + myP.name + " has special ingredients; do you want to delete it?")) {
            console.log('Dialog OK button selected');
            this.pizzaService.delete(myP.id)
              .subscribe(() => {
                console.log('pizza (id = ' + myP.id + ', name = ' + myP.name + ') deleted');
                // this.pizzas = this.pizzas.filter(p => p.id === myP.id);

                this.getPizzas();
              });
          } else {
            console.log('Dialog Cancel button selected');
          }
        }

        //    3. reload list of pizzas
        // this.getPizzas();
      });

    console.log("delete step by step handler finished");
  }

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
