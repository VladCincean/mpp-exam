import {Injectable} from "@angular/core";
import {Http, Response, Headers} from "@angular/http";
import {Pizza} from "./pizza.model";
import {Observable} from "rxjs/Observable";

import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import {Ingredient} from "./ingredient.model";

@Injectable()
export class PizzaService {
  private pizzasUrl = 'http://localhost:8080/api/pizzas';
  private pizzasUpdateAllUrl = 'http://localhost:8080/api/pizzas/updateAll';
  private pizzaIngredientsUrl = 'http://localhost:8080/api/pizzas/ingredients';
  private pizzaDeleteSelectedUrl = 'http://localhost:8080/api/pizzas/deleteSelected';

  private headers = new Headers({
    'Content-Type': 'application/json'
  });

  constructor(private http: Http) {
  }

  private extractPizzasData(res: Response) {
    let body = res.json();
    return body.pizzas || {};
  }

  private extractPizzaData(res: Response) {
    let body = res.json();
    return body.pizza || {};
  }

  private extractAllIngredientsData(res: Response) {
    let body = res.json();
    return body.ingredients || {};
  }

  private handleError(error: Response | any) {
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Observable.throw(errMsg);
  }

  getPizzas(): Observable<Pizza[]> {
    return this.http
      .get(this.pizzasUrl)
      .map(this.extractPizzasData)
      .catch(this.handleError);
  }

  getPizza(id: number): Observable<Pizza> {
    return this.getPizzas()
      .map(pizzas => pizzas.find(pizza => pizza.id === id));
  }

  getPizzaIngredients(id: number): Observable<Ingredient[]> {
    const url = `${this.pizzaIngredientsUrl}/${id}`;
    return this.http
      .get(url)
      .map(this.extractAllIngredientsData)
      .catch(this.handleError);
  }

  update(pizza: Pizza): Observable<Pizza> {
    console.log('pizza.service -- update -- entry');

    const url = `${this.pizzasUrl}/${pizza.id}`;

    console.log(pizza);

    console.log('pizza.service -- update -- end');

    return this.http
      .put(
        url,
        JSON.stringify({"pizza": pizza}),
        {headers: this.headers}
      ).map(this.extractPizzaData)
      .catch(this.handleError);
  }

  updateAll(priceAll: number): Observable<Pizza[]> {
    return this.http
      .put(
        this.pizzasUpdateAllUrl,
        JSON.stringify({"price": priceAll}),
        {headers: this.headers}
      ).map(this.extractPizzasData)
      .catch(this.handleError);
  }

  deleteSelected(pizzas: Pizza[]): Observable<Pizza[]> {
    console.log('service: deleteSelected: pizzas: ' + JSON.stringify({"pizzas": pizzas}));

    return this.http
      .put(
        this.pizzaDeleteSelectedUrl,
        JSON.stringify({"pizzas": pizzas}),
        {headers: this.headers}
      ).map(this.extractPizzasData)
      .catch(this.handleError);
  }

  delete(id: number): Observable<void> {
    const url = `${this.pizzasUrl}/${id}`;
    return this.http
      .delete(
        url,
        {headers: this.headers}
      ).map(() => null)
      .catch(this.handleError);
  }
}
