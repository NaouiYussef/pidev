import { Injectable } from '@angular/core';
import {ProductDetails} from "../product-details/product-details";
@Injectable({
  providedIn: 'root'
})
export class SharedService {
  private data!: ProductDetails[];

  setData(data: ProductDetails[]) {
    this.data = data;
  }

  getData(): ProductDetails[] {
    return this.data;
  }
}
