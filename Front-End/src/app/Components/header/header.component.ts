import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  isLoggedIn: boolean=true;

  userId?: number;
  constructor( private router:Router) {}

  ngOnInit() {
    

    const userId = localStorage.getItem('userId');
    this.userId = userId ? Number(userId) : undefined;
    
  }

  deconnextion(){
    localStorage.removeItem('userId');

    this.router.navigate(['/Auctions']);
   }
}
