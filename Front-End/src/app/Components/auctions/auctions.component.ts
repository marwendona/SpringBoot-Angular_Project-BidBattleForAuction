import { Component } from '@angular/core';
import { AuctionService } from 'src/app/Services/Auction/auction.service';
import { Auction } from 'src/app/models/Auction';

@Component({
  selector: 'app-auctions',
  templateUrl: './auctions.component.html',
  styleUrls: ['./auctions.component.css']
})
export class AuctionsComponent {
  auctions!: Auction[];

  constructor(private auctionService: AuctionService) {}

  ngOnInit() {
    this.auctionService.getAuctions().subscribe((auctions: Auction[]) => {
      this.auctions = auctions;
      console.log(auctions);
      console.log()
    });
  }

  
}
