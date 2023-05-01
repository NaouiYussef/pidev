import { Component, OnInit } from '@angular/core';

declare const $: any;
declare interface RouteInfo {
  path: string;
  title: string;
  class: string;
}
export const ROUTES: RouteInfo[] = [
  { path: '/admin/userlist', title: 'User List', class: '' },
  { path: '/admin/category', title: 'Category List', class: '' },
  { path: '/admin/product', title: 'Product',  class: '' },

];
@Component({
  selector: 'app-sidebar-back',
  templateUrl: './sidebar-back.component.html',
  styleUrls: ['../template-admin/template-admin.component.css']
})
export class SidebarBackComponent implements OnInit {

  menuItems!: any[];

  constructor() { }

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
    console.log(this.menuItems)
  }

}
