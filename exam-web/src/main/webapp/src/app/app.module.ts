import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import {AppRoutingModule} from "./app-routing.module";

import { AppComponent } from './app.component';
import { PizzasComponent } from './pizzas/pizzas.component';
import { PizzaListComponent } from './pizzas/pizza-list/pizza-list.component';
import { IngredientListComponent } from './pizzas/ingredient-list/ingredient-list.component';
import {PizzaService} from "./pizzas/shared/pizza.service";
import {IngredientService} from "./pizzas/shared/ingredient.service";

@NgModule({
  declarations: [
    AppComponent,
    PizzasComponent,
    PizzaListComponent,
    IngredientListComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
  ],
  providers: [PizzaService, IngredientService],
  bootstrap: [AppComponent]
})
export class AppModule { }
