import {Category} from "../category/category";
import {ProductDetails} from "../product-details/product-details";

export class Product {
  id!: number;
  productName!: string;
  region!: string;
  prix!: number;
  categoryp!: Category;
  details!: ProductDetails[];

}
