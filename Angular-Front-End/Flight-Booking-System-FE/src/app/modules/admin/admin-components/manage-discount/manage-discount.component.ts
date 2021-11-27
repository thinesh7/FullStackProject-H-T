import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-manage-discount',
  templateUrl: './manage-discount.component.html',
  styleUrls: ['./manage-discount.component.scss']
})
export class ManageDiscountComponent implements OnInit {

  discountApplied: boolean = false;
  isAlreadyAvailable:boolean = false;
  //AllAvailableDiscounts:
  discountList: any;

  //FormGroup:
  discountForm: FormGroup = new FormGroup({});

  constructor(private formBuilder: FormBuilder, private service: AdminService) { }

  ngOnInit(): void {
    this.initialize();
    this.getAllDiscounts();
  }

  initialize() {
    this.discountForm = this.formBuilder.group({
      discountCode: new FormControl('', Validators.required),
      discountPercentage: new FormControl(0, Validators.required)
    });
  }

  addDiscount() {
    this.service.addDiscount(this.discountForm.value).subscribe(data => {
      if (data == true){
      this.discountApplied = data;
      this.isAlreadyAvailable = false;
      this.discountForm.reset();
      this.getAllDiscounts();
      }else{
        this.isAlreadyAvailable = true;
        this.discountApplied = false;
      }
    },(err) => {
      console.log(err);
      this.isAlreadyAvailable = true;
      this.discountApplied = false;
    });
  }

  getAllDiscounts() {
    this.service.getAllDiscounts().subscribe(data => {
      this.discountList = data;
    });
  }

}
