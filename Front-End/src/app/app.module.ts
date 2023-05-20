import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './Components/footer/footer.component';
import { HomeComponent } from './Components/home/home.component';
import { HeaderComponent } from './Components/header/header.component';
import { AuctionsComponent } from './Components/auctions/auctions.component';
import { LoginComponent } from './Components/login/login.component';
import { SignupComponent } from './Components/signup/signup.component';


import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { HttpClientModule } from '@angular/common/http';
import { AuctionDetailComponent } from './Components/auction-detail/auction-detail.component';
import { AddAuctionComponent } from './Components/add-auction/add-auction.component';
import { ProfilComponent } from './Components/profil/profil.component';
import { UpdateUserComponent } from './Components/update-user/update-user.component';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HomeComponent,
    HeaderComponent,
    AuctionsComponent,
    LoginComponent,
    SignupComponent,
    AuctionDetailComponent,
    AddAuctionComponent,
    ProfilComponent,
    UpdateUserComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,

    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
