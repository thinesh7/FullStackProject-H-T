package com.flightapp.dto;

public class PassengerDTO {

	private String pName;
	private String trip;
    private String onwardSeat;
    private String pnr;
    
    public PassengerDTO() {}
    
	public PassengerDTO(String pName, String trip, String onwardSeat, String pnr) {
		this.pName = pName;
		this.trip = trip;
		this.onwardSeat = onwardSeat;
		this.pnr = pnr;
	}
	public String getpName() {
		return pName;
	}
	public String getTrip() {
		return trip;
	}
	public String getOnwardSeat() {
		return onwardSeat;
	}
	public String getPnr() {
		return pnr;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public void setTrip(String trip) {
		this.trip = trip;
	}
	public void setOnwardSeat(String onwardSeat) {
		this.onwardSeat = onwardSeat;
	}
	public void setPnr(String pnr) {
		this.pnr = pnr;
	}
	
	@Override
	public String toString() {
		return "PassengerDTO [pName=" + pName + ", trip=" + trip + ", onwardSeat=" + onwardSeat + ", pnr=" + pnr + "]";
	}
    
}
