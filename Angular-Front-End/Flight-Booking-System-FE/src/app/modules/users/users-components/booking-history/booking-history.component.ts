import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FlightServiceService } from 'src/app/service/flight-service.service';
import { jsPDF } from "jspdf";

@Component({
  selector: 'app-booking-history',
  templateUrl: './booking-history.component.html',
  styleUrls: ['./booking-history.component.scss']
})
export class BookingHistoryComponent implements OnInit {

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


  msgs: string[] = [];
  download(ticket: any) {
    this.msgs.push(''); this.msgs.push('');
    this.msgs.push(''); this.msgs.push('');
    this.msgs.push('PNR Number '+ticket.pnrNumber);
    this.msgs.push('Passenegr Name : ' + ticket.passengerName);
    this.msgs.push('Passenegr Gender : ' + ticket.passengerGender);
    this.msgs.push('Passenegr Age : ' + ticket.passengerAge);
    this.msgs.push('Email Id : ' + ticket.emailId);
    this.msgs.push('Flight Id: ' + ticket.flightId+ '   Flight Name :'+ticket.flightName);
    this.msgs.push('Passenegr Seat Number : ' + ticket.seatNumber);
    this.msgs.push('Source : ' + ticket.sourcePlace);
    this.msgs.push('Destination : ' + ticket.destinationPlace)
    this.msgs.push('OnBoarding Time : ' + ticket.departTime)
    this.msgs.push('Arrival Time : ' + ticket.arrivaltime)
    if(ticket.status){
      this.msgs.push('Ticket Status :  Completed')
    }else{
      this.msgs.push('Ticket Status :  Cancelled')
    }
    this.msgs.push('');
    this.msgs.push('Totail Amount Paid ' + ticket.ticketAmount)

    var doc = new jsPDF;
    doc.text(this.msgs, 8, 8);
    doc.save('Ticket_' + ticket.passengerName);
    this.msgs = [];

  }
}
