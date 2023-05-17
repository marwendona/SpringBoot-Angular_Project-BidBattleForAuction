import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/Services/Users/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  myForm!: FormGroup;
  constructor(private userService:UserService , private router:Router){}

  onSubmit() {
    this.userService.saveDataE(this.myForm.value).subscribe(response => {
      console.log(response);
      this.router.navigate(['/Login']);
    });  
  }

  ngOnInit() {
    this.myForm = new FormGroup({
      firstName: new FormControl(''),
      lastName: new FormControl(''),
      email: new FormControl(''),
    });
  }
}
