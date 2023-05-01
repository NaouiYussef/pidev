import { Component } from '@angular/core';
import {Category} from "../category";
import {CategoryService} from "../category.service";
import { Router, ActivatedRoute, NavigationEnd } from '@angular/router';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-category-update',
  templateUrl: './category-update.component.html',
  styleUrls: ['./category-update.component.css']
})
export class CategoryUpdateComponent {
  category!: Category;
  constructor(private categoryService: CategoryService,private router: Router, private route: ActivatedRoute) { }
  ngOnInit(): void {
    this.category = history.state?.category;
    console.log(this.category);

  }
  UpdateCategory() {
    this.categoryService.updateCategory(this.category).subscribe( data =>{
        console.log(data);
      }
      , error => console.log(error));
    this.redirectToCategory()

  }
  redirectToCategory() {
    this.router.navigate(['/admin/category']); // Use the router.navigate() method to navigate to the new category component
  }
}
