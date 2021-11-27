import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SearchResult } from '../model/flight-search-result';

@Injectable({
  providedIn: 'root'
})
export class FlightServiceService {

  paymentSuccess: any;

  constructor(private http: HttpClient) { }

  private adminUrl = "http://localhost:8989/users/api/v1.0/users";

  doAuthentication(userDetails: any) {
    return this.http.post(this.adminUrl + "/authenticate", userDetails);
  }

  addHeaderTotheUrl(): HttpHeaders {
    let headers = new HttpHeaders();
    let token = localStorage.getItem("token");
    token = 'Bearer ' + token;
    console.log(token);
    headers = headers.append('content-type', 'application/json')
    headers = headers.append('Authorization', token);
    return headers;
  }

  //Get List of Airports API:
  getAllAirportList(): Observable<string[]> {
    return this.http.get<string[]>(this.adminUrl + "/airports-location-list", { 'headers': this.addHeaderTotheUrl() });
  }

  //Get Search Result:
  getSearchResult(searchRequest: any): Observable<SearchResult[]> {
    return this.http.post<SearchResult[]>(this.adminUrl + "/search-flights", searchRequest, { 'headers': this.addHeaderTotheUrl() });
  }

  //Send booking Details:
  sendTicketBookingDetails(ticket: any) {
    return this.http.post(this.adminUrl + "/book-ticket", ticket, { 'headers': this.addHeaderTotheUrl() });
  }

  // Get Booking - History:
  getBookedFlightDetails(emailid: any) {
    return this.http.get(this.adminUrl + "/get-tickets/" + emailid, { 'headers': this.addHeaderTotheUrl() });
  }

  cancelFlight(pnrNumber: number) {
    return this.http.delete(this.adminUrl + "/cancel-ticket/" + pnrNumber, { 'headers': this.addHeaderTotheUrl() });
  }

  // fetch Discount:
  getDiscountDetails() {
    return this.http.get(this.adminUrl + '/get-discounts', { 'headers': this.addHeaderTotheUrl() });
  }

  // Register New User - Customer For Login:
  registerNewUser(userDetails: any) {
    return this.http.post(this.adminUrl + "/register-user", userDetails);
  }

}
