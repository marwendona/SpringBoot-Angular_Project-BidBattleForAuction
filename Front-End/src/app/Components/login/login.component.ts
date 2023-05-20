import { Component } from '@angular/core';
import { FormGroup,Validators ,FormControl} from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/Services/Users/user.service';
import { Login } from 'src/app/models/Login';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  form!:FormGroup; 

  constructor(private userService: UserService, private router:Router) {}

  ngOnInit():void{
    this.intForm();  
    }

    intForm():void{     
      this.form=new FormGroup({
        email:new FormControl(null,[Validators.required]), 
        password:new FormControl(null,[Validators.required]),
     
      })
    }


  onsub():void{  

    console.log(this.form.value); 
  

    const newLogin : Login={
      email: this.form.value.email,
      password: this.form.value.password
    };

    this.userService.login(newLogin).subscribe((userId: number)=> {
      localStorage.setItem("userId", userId.toString());
      this.router.navigate(['/Auctions']);
    });
   }
}
