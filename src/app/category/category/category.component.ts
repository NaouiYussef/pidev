import { Component, OnInit } from '@angular/core';
import {response} from "express";
import {Category} from "../category";
import {CategoryService} from "../category.service";
import {NavigationExtras, Router} from "@angular/router";

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {
  categories!: Category[];
  category!: Category;
  constructor(private categoryService: CategoryService,private router: Router) { }
  ngOnInit(): void {
    this.category= new Category();
    this.categoryService.getCategory().subscribe(data => {
      this.categories = data;

    });

  }
  deleteCategory(cat: Category) {

    this.categoryService.DeleteCategory(cat.id).subscribe(
      data=>{
        console.log(data);

      });
    this.refreshPage()
  }
  UpdateCategory(cat: Category){
    const navigationExtras: NavigationExtras = {
      state: {
        category: cat
      }
    };

    // Use the router.navigate() method with the NavigationExtras object to navigate to the new category component
    this.router.navigate(['/update-category'], navigationExtras);
  }
  redirectToNewCategory() {
    this.router.navigate(['/new-category']); // Use the router.navigate() method to navigate to the new category component
  }
  refreshPage() {
    location.reload(); // Use the location.reload() method to refresh the page
  }
}
