import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FlightServiceService } from 'src/app/service/flight-service.service';

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.scss']
})
export class UserRegisterComponent implements OnInit {

  //Form Group:
  userRegisterForm: FormGroup = new FormGroup({});
  isRegistered: boolean = false;
  isAlreadyExist: boolean = false;

  constructor(private formBuilder: FormBuilder, private fService: FlightServiceService, private router:Router) { }

  ngOnInit(): void {
    this.initializeRegForm();
  }

  initializeRegForm() {
    this.userRegisterForm = this.formBuilder.group({
      name: new FormControl('', Validators.required),
      age: new FormControl(),
      emailId: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });
  }

  goHome(){
    this.router.navigate(['users/users-login']);
  }

  //Register New User
  registerNewUser() {
    this.fService.registerNewUser(this.userRegisterForm.value).subscribe(data => {
      if(data==true){
        console.log(data);
        this.isRegistered = true;
        this.userRegisterForm.reset();
      }else{
        console.log(data);
        this.isAlreadyExist = true;
      }
    },err=>{
      this.isAlreadyExist = true;
      console.log(err);
    });
  }

}
