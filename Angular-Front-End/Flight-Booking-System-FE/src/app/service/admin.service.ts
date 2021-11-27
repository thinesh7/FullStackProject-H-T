import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private httpClient: HttpClient) {
  }

  private adminUrl = "http://localhost:8989/admin/api/v1.0"  // <--- API Gateway Url

  //Admin Service:

  // Check Admin Login Details:
  doAuthentication(userDetails: any) {
    return this.httpClient.post(this.adminUrl + "/authenticate", userDetails);
  }

  addHeaderTotheUrl(): HttpHeaders {
    let headers = new HttpHeaders();
    let token = localStorage.getItem("token");
    token = 'Bearer ' + token;
    headers = headers.append('content-type', 'application/json')
    headers = headers.append('Authorization', token);
    return headers;
  }

  //Add New AirLine:
  addNewAriline(airlineData: any) {
    return this.httpClient.post(this.adminUrl + "/register-airline", airlineData, { 'headers': this.addHeaderTotheUrl() });
  }

  //Get All Airlines List:
  getListOfAllAirlines(): Observable<string[]> {
    return this.httpClient.get<string[]>(this.adminUrl + "/getAirlines", { 'headers': this.addHeaderTotheUrl() });
  }

  //Schedule Flight:
  scheduleFlight(scheduleFlight: any): Observable<boolean> {
    return this.httpClient.post<boolean>(this.adminUrl + "/schedule-flight", scheduleFlight, { 'headers': this.addHeaderTotheUrl() });
  }

  //getAll Scheduled Flights:
  getAllScheduledFlights() {
    return this.httpClient.get(this.adminUrl + '/getAll-scheduleFlights', { 'headers': this.addHeaderTotheUrl() });
  }

  //Delete Schedule Flights:
  deleteScheduledFlight(flight: any): Observable<boolean> {
    return this.httpClient.put<boolean>(this.adminUrl + '/cancel-scheduleFlights', flight, { 'headers': this.addHeaderTotheUrl() });
  }

  //Add New Discount:
  addDiscount(discount: any): Observable<boolean> {
    return this.httpClient.post<boolean>(this.adminUrl + "/add-discounts", discount, { 'headers': this.addHeaderTotheUrl() });
  }

  // AWS URL's:

  private awsUrl ="https://49w817qv1c.execute-api.us-east-2.amazonaws.com";

  //Get All Discounts:
  getAllDiscounts() {
    return this.httpClient.get(this.awsUrl + "/get-discounts-aws");
  }

  //Get All Booked Tickets:
  getBookedFlightDetails() {
    return this.httpClient.get(this.awsUrl + "/getAll-tickets-aws");
  }

}
