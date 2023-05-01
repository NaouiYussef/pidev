import { Component } from '@angular/core';

@Component({
  
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  selector: 'body',
  template: '<router-outlet></router-outlet>',
})
export class AppComponent {
  title = 'pi-dashboard';
}
