import { Component } from '@angular/core';
import { AuctionService } from '../../Services/Auction/auction.service';
import { User } from '../../models/User';
import { UserService } from '../../Services/Users/user.service';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuctionDetails } from 'src/app/models/AuctionDetails';
import { BidDetails } from 'src/app/models/BidDetails';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.scss']
})
export class ProfilComponent {

  myForm!: FormGroup;
  user!: User;
  userId?: number;
  auctions: AuctionDetails[] = [];
  bids: BidDetails[] = [];
  myForm2!: FormGroup;

  constructor(private userService: UserService, private router:Router) {}

  ngOnInit() {

    const userId = localStorage.getItem('userId');
    this.userId = userId ? Number(userId) : undefined;

    this.userService.getUserById(this.userId).subscribe((u: User) => {
      this.user = u;
      console.log(this.user);
      
    });

    this.userService.getAuctions(this.userId!).subscribe(auctions => { this.auctions = auctions; console.log(auctions) });
    this.userService.getBids(this.userId!).subscribe(bids => this.bids = bids);

    // this.myForm2 = new FormGroup({
    //   firstName: new FormControl(this.user.firstName,[Validators.required]), 
    //   lastName: new FormControl(this.user.lastName,[Validators.required]), 
    //   email: new FormControl(this.user.email,[Validators.required]), 
    //   password: new FormControl(null,[Validators.required]), 
    // });
    // alert(this.myForm2)
  }

  

   onSubmit(){
    
    // console.log(this.myForm.value);

    // const newUser : User={

    //   firstName:  this.myForm.value.firstName,
    //   lastName:  this.myForm.value.lastName,
    //   email:  this.myForm.value.email,
    //   password:  this.myForm.value.password
    // };

    // this.userService.putUser(newUser,this.userId).subscribe(()=>{   
    //   alert("sucede");
    //   this.ngOnInit();

    // });

   }

}
