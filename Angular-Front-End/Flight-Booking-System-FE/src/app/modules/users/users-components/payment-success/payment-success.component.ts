import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FlightServiceService } from 'src/app/service/flight-service.service';

@Component({
  selector: 'app-payment-success',
  templateUrl: './payment-success.component.html',
  styleUrls: ['./payment-success.component.scss']
})
export class PaymentSuccessComponent implements OnInit {

  result:any;

  constructor(private fservice: FlightServiceService, private router: Router) { }

  ngOnInit(): void {
    this.result = this.fservice.paymentSuccess;
  }

  goHome() {
    this.router.navigate(['/dashboard/searchFlight']);
  }

}
