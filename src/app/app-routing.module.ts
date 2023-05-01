import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SigninComponent } from 'src/app/signin/signin.component';
import { SignupComponent } from 'src/app/signup/signup.component';
import { BodyUserComponent } from './body-user/body-user.component';
import { UserComponent } from 'src/app/user/user.component';

import { AppComponent } from './app.component';
import { TemplateUserComponent}  from 'src/app/template-user/template-user.component'
import { TemplateAdminComponent } from './template-admin/template-admin.component';
import { BodyAdminComponent } from './body-admin/body-admin.component';
import {CategoryComponent} from "./category/category/category.component";
import {CategoryFormComponent} from "./category/category-form/category-form.component";
import {CategoryUpdateComponent} from "./category/category-update/category-update.component";
import {ProductDetailsComponent} from "./product-details/product-details.component";
import {ProductUpdateComponent} from "./Product/product-update/product-update.component";
import {ProductFormComponent} from "./Product/product-form/product-form.component";
import {ProductComponent} from "./Product/product/product.component";

const routes: Routes =  [
{  path:'admin',
  component:TemplateAdminComponent,
  children:[

    {path: '', component:BodyAdminComponent},
    {path: 'userlist', component:UserComponent},
    {path: 'category', component:CategoryComponent},
    { path: 'new-category', component: CategoryFormComponent },
    { path: 'update-category', component: CategoryUpdateComponent },
    { path: 'product', component: ProductComponent },
    { path: 'new-product', component: ProductFormComponent },
    { path: 'update-product', component: ProductUpdateComponent },
    { path: 'details', component: ProductDetailsComponent },


  ],data:{}

},{
  path:'',
  component: TemplateUserComponent,
  children:[
    {path: '', component:BodyUserComponent},

    {path: 'body', component:BodyUserComponent},

{path:'signin',component:SigninComponent},
{path:'signup',component:SignupComponent},


  ]
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
