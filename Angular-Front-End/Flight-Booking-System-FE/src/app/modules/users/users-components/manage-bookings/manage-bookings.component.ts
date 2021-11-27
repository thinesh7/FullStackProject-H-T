import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FlightServiceService } from 'src/app/service/flight-service.service';

@Component({
  selector: 'app-manage-bookings',
  templateUrl: './manage-bookings.component.html',
  styleUrls: ['./manage-bookings.component.scss']
})
export class ManageBookingsComponent implements OnInit {

  constructor(private route: Router, private service: FlightServiceService) { }

  ngOnInit(): void {
    this.getBookingHistory();
  }

  ticketHistory: any;

  // Search and Retrive Data:
  getBookingHistory() {
    var emailId = sessionStorage.getItem("emailId");
    this.service.getBookedFlightDetails(emailId).subscribe(data => {
      this.ticketHistory = data;
    });
  }


  cancelTicket(pnrNumber:number){
    confirm("Do you wnat to Cancel the Ticket PNR :"+pnrNumber+" ?");
    this.service.cancelFlight(pnrNumber).subscribe(data => {
      this.getBookingHistory();
    });
  }
}
