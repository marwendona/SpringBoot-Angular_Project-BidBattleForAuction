import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/Services/Users/user.service';
import { User } from 'src/app/models/User';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.scss']
})
export class UpdateUserComponent {
  user!: User;
  userId?: number;
  myForm!: FormGroup;

  constructor(private userService: UserService, private router:Router) {}

  ngOnInit() {

    const userId = localStorage.getItem('userId');
    this.userId = userId ? Number(userId) : undefined;

    this.userService.getUserById(this.userId).subscribe((u: User) => {
      this.user = u;
      console.log(this.user);

      this.myForm = new FormGroup({
        firstName: new FormControl(this.user.firstName,[Validators.required]), 
        lastName: new FormControl(this.user.lastName,[Validators.required]), 
        email: new FormControl(this.user.email,[Validators.required]), 
        password: new FormControl(null,[Validators.required]), 
      });
      
    });
    
    
  }

  

   onSubmit(){
    
    console.log(this.myForm.value);

    const newUser : User={

      firstName:  this.myForm.value.firstName,
      lastName:  this.myForm.value.lastName,
      email:  this.myForm.value.email,
      password:  this.myForm.value.password
    };

    this.userService.putUser(newUser,this.userId).subscribe(()=>{   
      alert("suc");
      this.router.navigate(['/Profile']);
    });

   }
}
