import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Category} from "./category";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  public host:string = "http://localhost:8080";

  constructor(private http:HttpClient) { }

  public getCategory():Observable<Category[]>{
    return this.http.get<Category[]>(this.host+"/category/showAll");
  }
  public getCategoryById(id: number): Observable<Category>{
    return this.http.get<Category>(this.host+"/category/showCatById/"+id);
  }
  public createCategory(category:Category):Observable<Object>{
    return this.http.post(this.host+"/category/addCat",category);
  }

  public updateCategory(category:Category): Observable<Object>{
    return this.http.put(this.host+"/category/updatecat",category);
  }

  public DeleteCategory(id: number): Observable<Object>{
    return this.http.delete(this.host+"/category/deleteCatbyID/"+id);

  }}
