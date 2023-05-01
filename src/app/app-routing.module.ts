import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CategoryComponent} from "./category/category/category.component";
import {CategoryFormComponent} from "./category/category-form/category-form.component";
import {CategoryUpdateComponent} from "./category/category-update/category-update.component";
import {ProductComponent} from "./Product/product/product.component";
import {ProductFormComponent} from "./Product/product-form/product-form.component";
import {ProductUpdateComponent} from "./Product/product-update/product-update.component";
import {ProductDetailsComponent} from "./product-details/product-details.component";

const routes: Routes =[
  {path: '', redirectTo: 'dashboard', pathMatch: 'full',},
  { path: 'category', component: CategoryComponent },
  { path: 'new-category', component: CategoryFormComponent },
  { path: 'update-category', component: CategoryUpdateComponent },
  { path: 'product', component: ProductComponent },
  { path: 'new-product', component: ProductFormComponent },
  { path: 'update-product', component: ProductUpdateComponent },
  { path: 'details', component: ProductDetailsComponent },


];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
