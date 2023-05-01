import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['../../assets/css/style.css']
})
export class HeaderComponent implements OnInit {
  token: any;
  userRole: string | null;

  constructor(private route: Router) {}

  ngOnInit(): void {
    this.token = localStorage.getItem('access_token');
    this.userRole = localStorage.getItem('user_role');
    console.log(this.token);
    console.log(this.userRole);
  }

  isLoggedIn(): boolean {
    return !!this.token;
  }

  isAdmin(): boolean {
    return this.userRole === 'admin';
  }

  logout() {
    localStorage.removeItem('access_token');
    localStorage.removeItem('refresh_token');
    localStorage.removeItem('user_role');
    this.route.navigate(['body']);
    this.token = false;
    this.userRole = null;
  }
}
