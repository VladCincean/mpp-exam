import {Component, Input, OnInit} from '@angular/core';
import {PizzaService} from "../shared/pizza.service";
import {Router} from "@angular/router";
import {Location} from "@angular/common";
import {ActivatedRoute, Params} from "@angular/router";
import {Pizza} from "../shared/pizza.model";
import {Ingredient} from "../shared/ingredient.model";

import 'rxjs/add/operator/switchMap';

@Component({
  moduleId: module.id,
  selector: 'app-ingredient-list',
  templateUrl: './ingredient-list.component.html',
  styleUrls: ['./ingredient-list.component.css']
})
export class IngredientListComponent implements OnInit {

  @Input()
  pizza: Pizza;

  ingredients: Ingredient[];

  constructor(
    private pizzaService: PizzaService,
    private route: ActivatedRoute,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.route.params
      .switchMap(
        (params: Params) => this.pizzaService.getPizzaIngredients(+params['id'])
      ).subscribe(ingredients => this.ingredients = ingredients);
  }

  goBack(): void {
    this.location.back();
  }

}
