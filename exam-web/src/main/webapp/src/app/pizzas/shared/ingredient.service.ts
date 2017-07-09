import {Injectable} from "@angular/core";
import {Headers, Http, Response} from "@angular/http";
import {Observable} from "rxjs/Observable";

import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import {Ingredient} from "./ingredient.model";

@Injectable()
export class IngredientService {
  private ingredientsUrl = 'http://localhost:8080/api/ingredients';
  private headers = new Headers({
    'Content-Type': 'application/json'
  });

  constructor (private http: Http) {
  }

  private extractIngredientsData(res: Response) {
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

  getIngredients(): Observable<Ingredient[]> {
    return this.http.get(this.ingredientsUrl)
      .map(this.extractIngredientsData)
      .catch(this.handleError);
  }

  getIngredient(id: number): Observable<Ingredient> {
    return this.getIngredients()
      .map(ingredients => ingredients.find(ingredient => ingredient.id === id));
  }
}
