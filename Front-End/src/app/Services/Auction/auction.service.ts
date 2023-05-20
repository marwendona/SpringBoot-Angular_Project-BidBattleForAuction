import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuctionDetails } from 'src/app/models/AuctionDetails';
import { Bid } from 'src/app/models/Bid';

@Injectable({
  providedIn: 'root'
})
export class AuctionService {
  
  constructor(private _httpClient: HttpClient){}

  private apiUrlUser = 'http://localhost:8085/api/v1/users';

  private apiUrlAuction = 'http://localhost:8085/api/v1/auctions';
  getAuctions(): Observable<AuctionDetails[]> {
    return this._httpClient.get<AuctionDetails[]>(this.apiUrlAuction);
  }
  saveDataA(userId: number, data: any) : Observable<number> {
    return this._httpClient.post<number>(this.apiUrlUser + "/" + userId + "/auctions", data);
  }

  getAuction(id: number) : Observable<AuctionDetails> {
    return this._httpClient.get<AuctionDetails>(this.apiUrlAuction + "/" + id);
  }
 
  addBid(auctionId: number, bid: Bid): Observable<number>  {
    return this._httpClient.post<number>(this.apiUrlAuction + "/" + auctionId + "/bids", bid);
  }

  getBids(id: number) : Observable<Bid[]> {
    return this._httpClient.get<Bid[]>(this.apiUrlAuction + "/" + id + "/bids");
  }
}
