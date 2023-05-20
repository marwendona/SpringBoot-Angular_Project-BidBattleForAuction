import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuctionDetails } from 'src/app/models/AuctionDetails';
import { BidDetails } from 'src/app/models/BidDetails';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }


  private apiUrlUser = 'http://localhost:8085/api/v1/users';
  saveDataE(data: any) {
    return this.http.post(this.apiUrlUser, data);
  }

  private apiUrlUsers = 'http://localhost:8085/api/v1/auth/login';
  public login(data: any): any {
    return this.http.post(this.apiUrlUsers, data);
  }

  private apiUser = 'http://localhost:8085/api/v1/users/2';
  public getUserById(id: any): Observable<any> {
    return this.http.get<any>('http://localhost:8085/api/v1/users/' + id);
  }

  getAuctions(id: number): Observable<AuctionDetails[]> {
    return this.http.get<AuctionDetails[]>(this.apiUrlUser + "/" + id + "/auctions");
  }

  getBids(id: number): Observable<BidDetails[]> {
    return this.http.get<BidDetails[]>(this.apiUrlUser + "/" + id + "/bids");
  }

  
putUser(data: any,idUser:any):any {
  return this.http.put('http://localhost:8085/api/v1/users/'+idUser, data);
}

}
