import { Component } from '@angular/core';
import {Category} from "../../category/category";
import {CategoryService} from "../../category/category.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Product} from "../product";
import {ProductService} from "../product.service";
import {Attribute} from "../../product-details/attribute";
import {ProductDetails} from "../../product-details/product-details";

@Component({
  selector: 'app-product-update',
  templateUrl: './product-update.component.html',
  styleUrls: ['./product-update.component.css']
})
export class ProductUpdateComponent {
  product!: Product;
  products!: Product[];
  category!: Category;
  detail!: ProductDetails;
  details!: ProductDetails[];
  categoryid!: number;
  categories!: Category[];
  attr!: Attribute
  fieldsetArray: { attr: Attribute, detail: ProductDetails }[] = [];

  constructor(private productService: ProductService,private categoryService: CategoryService,private router: Router, private route: ActivatedRoute) { }
  ngOnInit(): void {
    this.categoryService.getCategory().subscribe(data => {
      this.categories = data;

    });
    this.product=new Product();
    this.category=new Category();
    this.product = history.state?.product;
    this.category = this.product.categoryp
    this.categoryid=this.category.id
    for (let i = 0; i < this.product.details.length; i++){
      this.fieldsetArray.push({ attr: this.product.details[i].attribut, detail: this.product.details[i] });
    }

  }
  addFieldCount() {
    this.fieldsetArray.push({ attr: new Attribute(), detail: new ProductDetails() });
  }
  updateProduct() {
    this.product=new Product();

    console.log(this.fieldsetArray)
    this.details=[];
    for (let i = 0; i < this.fieldsetArray.length; i++) {
      this.detail=new ProductDetails()
      this.attr=new Attribute()
      this.attr= this.fieldsetArray[i].attr;
      this.detail= this.fieldsetArray[i].detail;
      this.detail.attribut=this.attr
      this.details.push(this.detail);
    }
    this.categoryService.getCategoryById(this.categoryid).subscribe(
      data => {
        this.category.id = data.id;
        this.category.categoryName = data.categoryName;

        this.product.categoryp = this.category;
        this.product.details= this.details
        this.productService.updateProduct(this.product).subscribe(
          (response) => {
            console.log('Product created successfully!', response);
            // Perform any other actions after category creation
          },
          (error) => {
            console.error('Failed to create product:', error);
            // Handle error appropriately
          }
        );
      },
      error => {
        console.error(error);
      }
    );
    this.redirectToProduct()
  }
  redirectToProduct() {
    this.router.navigate(['/admin/product']); // Use the router.navigate() method to navigate to the new category component
  }
}
