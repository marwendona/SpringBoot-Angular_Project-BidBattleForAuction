import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuctionService } from 'src/app/Services/Auction/auction.service';
import { Auction } from 'src/app/models/Auction';

@Component({
  selector: 'app-add-auction',
  templateUrl: './add-auction.component.html',
  styleUrls: ['./add-auction.component.css']
})
export class AddAuctionComponent  implements OnInit {
  myForm!: FormGroup;
  constructor(private auctionService:AuctionService , private router:Router){}
  ngOnInit(): void {
    this.myForm = new FormGroup({
      name: new FormControl(null, Validators.required),
      category: new FormControl(null, Validators.required),
    minPrice: new FormControl(null, Validators.required),
    endDate: new FormControl(null, Validators.required),
    auctionStatus: new FormControl(null, Validators.required)
  });
  console.log(this.myForm)
}
  onSubmit() {
    const newAuction : Auction={
      id: 10,
      product: {
        name: this.myForm.value.name,
        category: this.myForm.value.category
      },

      minPrice: this.myForm.value.minPrice,
      endDate: this.myForm.value.endDate,
      auctionStatus: this.myForm.value.auctionStatus
      
    };
    this.auctionService.saveDataA(newAuction).subscribe(()=> {
      // console.log(response);
      this.router.navigate(['/Auctions']);
      
    });
 
  }

}
