import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { CategoryComponent } from './category/category/category.component';
import { CategoryFormComponent } from './category/category-form/category-form.component';
import {HttpClientModule} from "@angular/common/http";
import {CategoryService} from "./category/category.service";
import {FormsModule} from "@angular/forms";
import { CategoryUpdateComponent } from './category/category-update/category-update.component';
import { ProductComponent } from './Product/product/product.component';
import { ProductFormComponent } from './Product/product-form/product-form.component';
import { ProductUpdateComponent } from './Product/product-update/product-update.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { NavbarFrontComponent } from './navbar-front/navbar-front.component';
import { FooterFrontComponent } from './footer-front/footer-front.component';

@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    NavbarComponent,
    FooterComponent,
    CategoryComponent,
    CategoryFormComponent,
    CategoryUpdateComponent,
    ProductComponent,
    ProductFormComponent,
    ProductUpdateComponent,
    ProductDetailsComponent,
    NavbarFrontComponent,
    FooterFrontComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule

  ],
  providers: [CategoryService],
  bootstrap: [AppComponent]
})
export class AppModule { }
