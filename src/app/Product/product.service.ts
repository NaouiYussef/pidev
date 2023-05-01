import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "./product";
@Injectable({
  providedIn: 'root'
})
export class ProductService {

  public host:string = "http://localhost:8080/Spring/Product";

  constructor(private http:HttpClient) { }

  public getProduct():Observable<Product[]>{
    return this.http.get<Product[]>(this.host+"/showAll");
  }
  public getProductById(id: number): Observable<Product>{
    return this.http.get<Product>(this.host+"/showProdById/"+id);
  }
  public createProduct(product:Product):Observable<Object>{
    return this.http.post(this.host+"/addprod",product);
  }

  public updateProduct(product:Product): Observable<Object>{
    return this.http.put(this.host+"/updateprod",product);
  }

  public DeleteProduct(id: number): Observable<Object>{
    return this.http.delete(this.host+"/deleteProdbyID/"+id);

  }

}
