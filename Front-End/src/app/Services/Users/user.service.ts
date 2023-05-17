import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient){}


  private apiUrlUser = 'http://localhost:8085/api/v1/users';
  saveDataE(data: any) {
    return this.http.post(this.apiUrlUser, data);
  }
}
