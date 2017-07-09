import {RouterModule, Routes} from "@angular/router";
import {PizzaListComponent} from "./pizzas/pizza-list/pizza-list.component";
import {IngredientListComponent} from "./pizzas/ingredient-list/ingredient-list.component";
import {NgModule} from "@angular/core";

const routes: Routes = [
  { path: 'pizzashop2010/pizzas', component: PizzaListComponent },
  { path: 'pizzashop2010/pizza/:id', component: IngredientListComponent },
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
