import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.scss']
})
export class ReportsComponent implements OnInit {

  ScheduledAllFlightDetails: any;

  constructor(private service: AdminService) { }

  ngOnInit(): void {
    this.initializeScheduledFlightDetails();
  }

  initializeScheduledFlightDetails() {
    this.service.getAllScheduledFlights().subscribe(data => {
      this.ScheduledAllFlightDetails = data;
      console.log(data);
    });
  }


}
