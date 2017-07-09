import {Ingredient} from "./ingredient.model";

export class Pizza {
  id: number;
  name: string;
  description: string;
  price: string;
  ingredients: Ingredient[];
}
