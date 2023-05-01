import { Component } from '@angular/core';
import {Category} from "../category";
import {CategoryService} from "../category.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-category-form',
  templateUrl: './category-form.component.html',
  styleUrls: ['./category-form.component.css']
})
export class CategoryFormComponent {
  category!: Category;
  constructor(private categoryService: CategoryService,private router: Router) { }
  ngOnInit(): void {
    this.category=new Category();
  }
  createCategory(): void {
    this.categoryService.createCategory(this.category).subscribe(

      (response) => {
        console.log('Category created successfully!', response);
        // Perform any other actions after category creation
      }
      ,
      (error) => {
        console.error('Failed to create category:', error);
        // Handle error appropriately
      }
    );
    this.redirectToCategory()
  }

  redirectToCategory() {
    this.router.navigate(['/admin/category']); // Use the router.navigate() method to navigate to the new category component
  }

}
