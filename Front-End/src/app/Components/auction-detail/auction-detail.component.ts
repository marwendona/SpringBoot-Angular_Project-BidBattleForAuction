import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-auction-detail',
  templateUrl: './auction-detail.component.html',
  styleUrls: ['./auction-detail.component.css']
})
export class AuctionDetailComponent {
  auctionId: number=0;
  auctionMin!:number;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    // this.auctionId = +this.route.snapshot.queryParamMap.get('auctionId');
  }
}
