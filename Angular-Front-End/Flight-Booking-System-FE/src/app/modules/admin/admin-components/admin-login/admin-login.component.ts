import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.scss']
})
export class AdminLoginComponent implements OnInit {

  //Form Grroup:
  loginFormGroup: FormGroup = new FormGroup({});
  //Login Check:
  isLoginFail: boolean = false;

  //Controlls:
  emailid = new FormControl("Thinesh@admin.com", [Validators.required, Validators.pattern("([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9])+\.)+([a-zA-Z]{2,4})")]);
  pswd = new FormControl("admin", Validators.required);

  constructor(private formBuilder: FormBuilder, private router: Router, private service: AdminService) { }

  ngOnInit(): void {
    this.initializationFromBuilder();
  }

  //All Method:
  initializationFromBuilder() {
    this.loginFormGroup = this.formBuilder.group({
      username: this.emailid,
      password: this.pswd
    });
  }

  login() {
    this.doGeLoginDetails();
  }

  token:any;
  doGeLoginDetails() {
    this.service.doAuthentication(this.loginFormGroup.value).subscribe(data=>{
      if(!!data){
        this.token = data;
        console.log(this.token.jwttoken);
        localStorage.setItem("token", this.token.token);
        localStorage.setItem("isValid", "true");
        console.log(localStorage.getItem("token"));
      this.forwardRequestToNextPage();
      }else{
        this.isLoginFail = true;
      }
    },err=>{
      this.isLoginFail = true;
      console.log(err);
    });
  }

  forwardRequestToNextPage() {
    this.router.navigate(["admin/dashboard/add-airline"]);
  }
}
