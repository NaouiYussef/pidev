import {Component, OnInit} from '@angular/core';
import {Product} from "../product";
import {ProductService} from "../product.service";
import {Category} from "../../category/category";
import {CategoryService} from "../../category/category.service";
import {NavigationExtras, Router} from "@angular/router";
import {SharedService} from "../../shared.service";


@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})

export class ProductComponent implements OnInit {
  currentPage: number = 1;
  pageSize: number = 2;
  totalItems: number = 0;
  minPrice: number = 0;
  maxPrice: number = 0;
  products!: Product[];
  product!: Product;




  constructor(private productService: ProductService, private sharedService: SharedService,private router: Router) { }
  ngOnInit(): void {
    this.product= new Product();
    this.productService.getProduct().subscribe(data => {
      this.products = data;
        this.totalItems = this.products.length;

    }
    );

  }
  deleteProduct(prod: Product) {

    this.productService.DeleteProduct(prod.id).subscribe(
      data=>{
        console.log(data);

      });
    this.refreshPage()
  }
  UpdateProduct(prod: Product){
    const navigationExtras: NavigationExtras = {
      state: {
        product: prod
      }
    };

    // Use the router.navigate() method with the NavigationExtras object to navigate to the new category component
    this.router.navigate(['/update-product'], navigationExtras);
  }
  redirectToNewProduct() {
    this.router.navigate(['/new-product']); // Use the router.navigate() method to navigate to the new category component
  }
  refreshPage() {
    location.reload(); // Use the location.reload() method to refresh the page
  }
  sendData(id: number) {
    this.products.forEach((product : Product)=>{
      if (product.id==id) {
        console.log(product)
        this.sharedService.setData(product.details);

      }
    });
    this.router.navigate(['/details']);

  }
  public searchProds(key: string): void {
    console.log(key);
    const results: Product[] = [];
    for (const prod of this.products) {
      if (prod.productName.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || prod.categoryp.categoryName.toLowerCase().indexOf(key.toLowerCase()) !== -1
      ) {
        results.push(prod);
      }
    }
    this.products = results;
    this.totalItems = this.products.length;
    if (results.length === 0 || !key) {

      this.productService.getProduct();
      this.refreshPage();
    }

  }

  totalPages(): number {
    return Math.ceil(this.totalItems / this.pageSize);
  }

  filterByPrice(minPrice: number, maxPrice: number): void {
    // Check if minPrice is higher than maxPrice
    if (minPrice > maxPrice) {
      console.log("Min Price cannot be higher than Max Price");
      return; // Exit the function early
    }

    const filteredProducts = this.products.filter((product: Product) => {
      const productPrice = product.prix;
      return productPrice >= minPrice && productPrice <= maxPrice;
    });

    // Update the products array with the filtered results
    this.products = filteredProducts;
  }




}
