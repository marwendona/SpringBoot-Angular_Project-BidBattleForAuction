import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Auction } from 'src/app/models/Auction';

@Injectable({
  providedIn: 'root'
})
export class AuctionService {

  constructor(private _httpClient: HttpClient){}

  private apiUrlAAuction = 'http://localhost:8085/api/v1/users/1/auctions';

  private apiUrlAuction = 'http://localhost:8085/api/v1/auctions';
  getAuctions(): Observable<Auction[]> {
    return this._httpClient.get<Auction[]>(this.apiUrlAuction);
  }
  saveDataA(data: any) {
    return this._httpClient.post(this.apiUrlAAuction, data);
  }
 
}
