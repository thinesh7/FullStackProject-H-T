import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { SearchDetails } from 'src/app/model/search-details.bean';
import { FlightServiceService } from 'src/app/service/flight-service.service';
import { Router } from '@angular/router';
import { Ticket } from 'src/app/service/ticket';

@Component({
  selector: 'app-book-flight',
  templateUrl: './book-flight.component.html',
  styleUrls: ['./book-flight.component.scss']
})

export class BookFlightComponent implements OnInit {

  //constructor
  constructor(private formBuilder: FormBuilder, private flightService: FlightServiceService, private router: Router,
    private tservice: Ticket) {
  }

  //Form builder
  flightSearchForm: FormGroup = new FormGroup({});

  //Form Controls:
  tType = new FormControl("", Validators.required);
  sPlace = new FormControl("", Validators.required);
  dPlace = new FormControl("", Validators.required);
  dDate = new FormControl("", Validators.required);
  rDate = new FormControl();

  //  List of AirPorts For Source Place:
  public listOfAirportsForSourcePlace: string[] = [];
  public listOfFilteredAirportsForSourcePlace: string[] = [];

  // List of AirPorts:
  public listOfAirportsForDestinationPlace: string[] = [];
  public listOfFilteredAirportsForDestinationPlace: string[] = [];

  ngOnInit(): void {
    this.flightSearchForm = this.formBuilder.group({
      tripType: this.tType,
      sourcePlace: this.sPlace,
      destinationPlace: this.dPlace,
      departureDate: this.dDate,
      returnDate: this.rDate
    });

    this.loadAirportListForSource();
    this.autoCompCallForSource();

    this.loadAirportListForDestination();
    this.autoCompCallForDestination();
  }

  loadAirportListForSource(): void {
    this.flightService.getAllAirportList().subscribe(data => {
      this.listOfAirportsForSourcePlace = data;
      this.listOfFilteredAirportsForSourcePlace = data;
    });
  }

  private autoCompCallForSource() {
    this.flightSearchForm.get('sourcePlace')?.valueChanges.subscribe(enteredData => this.filterData(enteredData));
  }

  private filterData(enteredData: string) {
    this.listOfFilteredAirportsForSourcePlace = this.listOfAirportsForSourcePlace.filter(item => {
      return item.toLowerCase().indexOf(enteredData.toLowerCase()) > -1
    })
  }

  //Destination Logic:
  loadAirportListForDestination() {
    this.flightService.getAllAirportList().subscribe(data => {
      console.log('Any Reply> '+data);
      this.listOfAirportsForDestinationPlace = data;
      this.listOfFilteredAirportsForDestinationPlace = data;
    });
  }

  private autoCompCallForDestination() {
    this.flightSearchForm.get('destinationPlace')?.valueChanges.subscribe(enter => { this.filterData2(enter) });
  }
  
  private filterData2(enter: string) {
    this.listOfFilteredAirportsForDestinationPlace = this.listOfAirportsForDestinationPlace.filter(item => {
      return item.toLowerCase().indexOf(enter.toLowerCase()) > -1;
    })
  }

  restForm() {
    this.flightSearchForm.reset();
    this.listOfFilteredAirportsForSourcePlace = this.listOfAirportsForSourcePlace;
    this.listOfFilteredAirportsForDestinationPlace = this.listOfAirportsForDestinationPlace;
  }

  sendData() {
    let searchDeatils: SearchDetails = this.flightSearchForm.value;

    //Send this to Server and get Search Result:
    this.tservice.searchDetails = searchDeatils;

    //Redirect to 
    this.router.navigate(['dashboard/search-result']);
  }

}
