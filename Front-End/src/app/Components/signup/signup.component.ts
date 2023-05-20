import { Component } from '@angular/core';
import { FormGroup,Validators ,FormControl} from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/Services/Users/user.service';
import { User } from 'src/app/models/User';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  myForm!: FormGroup;
  constructor(private userService:UserService , private router:Router){}
  ngOnInit() {
    this.myForm = new FormGroup({
      firstName: new FormControl(null,[Validators.required]), 
      lastName: new FormControl(null,[Validators.required]), 
      email: new FormControl(null,[Validators.required]), 
      password: new FormControl(null,[Validators.required]), 
    });
  }

  onSubmit() {

    console.log(this.myForm.value);

    const newUser : User={

      firstName:  this.myForm.value.firstName,
      lastName:  this.myForm.value.lastName,
      email:  this.myForm.value.email,
      password:  this.myForm.value.password
    };


    this.userService.saveDataE(newUser).subscribe(response => {
      console.log(response);
      this.router.navigate(['/Login']);
    });  
  }


}
