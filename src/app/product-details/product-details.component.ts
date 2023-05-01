import { Component, OnInit } from '@angular/core';
import {SharedService} from "../shared.service";
import {ProductDetails} from "./product-details";

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {
  receivedData: ProductDetails[];
  productDetails! : ProductDetails[];
  constructor(private sharedService: SharedService) {
    this.receivedData = this.sharedService.getData();
  }
  ngOnInit(): void {
    this.productDetails = this.receivedData;
  }

}
