import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './Components/home/home.component';
import { AuctionsComponent } from './Components/auctions/auctions.component';
import { LoginComponent } from './Components/login/login.component';
import { SignupComponent } from './Components/signup/signup.component';
import { AuctionDetailComponent } from './Components/auction-detail/auction-detail.component';
import { AddAuctionComponent } from './Components/add-auction/add-auction.component';
import { ProfilComponent } from './Components/profil/profil.component';
import { UpdateUserComponent } from './Components/update-user/update-user.component';

const routes: Routes = [
  {
    path: 'UpdateUser',
    pathMatch: 'full',
    component: UpdateUserComponent
  },
  {
    path: 'AddAuction',
    pathMatch: 'full',
    component: AddAuctionComponent
  },
  {
    path: ':id/AuctionDetail',
    pathMatch: 'full',
    component: AuctionDetailComponent
  },
  {
    path: 'Signup',
    pathMatch: 'full',
    component: SignupComponent
  },
  {
    path: 'Login',
    pathMatch: 'full',
    component: LoginComponent
  },
  {
    path: 'Auctions',
    pathMatch: 'full',
    component: AuctionsComponent
  },
  {
    path: 'Home',
    pathMatch: 'full',
    component: HomeComponent
  },

  {
    path: 'Profile',
    pathMatch: 'full',
    component: ProfilComponent
  },
  //--------------Default-------------

  {
    path: '**',
    pathMatch: 'full',
    redirectTo: "Home"
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
