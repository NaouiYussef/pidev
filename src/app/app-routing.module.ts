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


import { PostComponent } from './post/view-post/post.component';
import { CreatePostComponent } from './post/create-post/create-post.component';
import { EditPostComponent } from './post/edit-post/edit-post.component';
import { ViewSubredditComponent } from './subreddit/view-subreddit/view-subreddit.component';
import { CreateSubredditComponent } from './subreddit/create-subreddit/create-subreddit.component';
import { UpdateSubredditComponent } from './subreddit/update-subreddit/update-subreddit.component';
import { ViewCommentComponent } from './comment/view-comment/view-comment.component';
import { CreateCommentComponent } from './comment/create-comment/create-comment.component';

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

    { path: 'post', component: PostComponent },
    { path: 'create-post', component: CreatePostComponent},
    { path: 'edit-post/:id', component: EditPostComponent},
    { path: 'view-subreddit', component: ViewSubredditComponent},
    { path: 'create-subreddit', component: CreateSubredditComponent},
    { path: 'update-subreddit/:id', component: UpdateSubredditComponent},
    { path: 'view-comment', component:ViewCommentComponent},
    { path: 'create-comment', component: CreateCommentComponent},


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
