import { Component } from '@angular/core';
import { AuctionService } from 'src/app/Services/Auction/auction.service';
import { AuctionDetails } from 'src/app/models/AuctionDetails';

@Component({
  selector: 'app-auctions',
  templateUrl: './auctions.component.html',
  styleUrls: ['./auctions.component.css']
})
export class AuctionsComponent {
  auctions!: AuctionDetails[];

  constructor(private auctionService: AuctionService) {}

  ngOnInit() {
    this.auctionService.getAuctions().subscribe((auctions: AuctionDetails[]) => {
      this.auctions = auctions;
      console.log(auctions);
      console.log()
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    console.log(filterValue);
    if(filterValue!=""){
      this.auctions = this.auctions.filter(item => item.product.name
        .toLowerCase().startsWith(filterValue
        .toLowerCase()));
    }
    if(filterValue=="") {
      this.ngOnInit();
    }
  }
}
