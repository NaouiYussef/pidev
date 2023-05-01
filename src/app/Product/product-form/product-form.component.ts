import {ChangeDetectorRef, Component, ComponentRef, NgIterable, ViewChild, ViewContainerRef} from '@angular/core';
import {Category} from "../../category/category";
import {CategoryService} from "../../category/category.service";
import {Router} from "@angular/router";
import {Product} from "../product";
import {ProductService} from "../product.service";
import {Observable} from "rxjs";
import {ProductDetails} from "../../product-details/product-details";
import {Attribute} from "../../product-details/attribute";




@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',

  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent {
  product!: Product;
  products!: Product[];
  category!: Category;
  detail!: ProductDetails;
  details!: ProductDetails[];
  categoryid!: number;
  categories!: Category[];
  attr!: Attribute
  fieldsetArray: { attr: Attribute, detail: ProductDetails }[] = [];
  constructor(private productService: ProductService, private categoryService: CategoryService, private router: Router) {
  }

  ngOnInit(): void {
    this.categoryService.getCategory().subscribe(data => {
      this.categories = data;

    });
    this.product = new Product();
    this.category = new Category();
    this.detail = new ProductDetails();
    this.attr=new Attribute();
  }

  createProduct(): void {

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
        console.log(this.product);
        this.productService.createProduct(this.product).subscribe(
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
    this.router.navigate(['/product']); // Use the router.navigate() method to navigate to the new category component
  }
  addFieldCount() {
    this.fieldsetArray.push({ attr: new Attribute(), detail: new ProductDetails() });
  }

}
