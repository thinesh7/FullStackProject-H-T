package com.flightapp.beans;

import java.util.List;

public class BookingRequest {

	private String name;
	private String email;
    private String tripType;
	private List<PassengerRequest> passengersList;
	private String discount;
    private double billingAmmount;
    private Long onwardScheduleId;
    private Long roundScheduleId;
    
    public BookingRequest() {}
	
    public BookingRequest(String name, String email, String tripType, List<PassengerRequest> passengersList,
			String discount, double billingAmmount, Long onwardScheduleId, Long roundScheduleId) {
		this.name = name;
		this.email = email;
		this.tripType = tripType;
		this.passengersList = passengersList;
		this.discount = discount;
		this.billingAmmount = billingAmmount;
		this.onwardScheduleId = onwardScheduleId;
		this.roundScheduleId = roundScheduleId;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getTripType() {
		return tripType;
	}

	public List<PassengerRequest> getPassengersList() {
		return passengersList;
	}

	public String getDiscount() {
		return discount;
	}

	public double getBillingAmmount() {
		return billingAmmount;
	}

	public Long getOnwardScheduleId() {
		return onwardScheduleId;
	}

	public Long getRoundScheduleId() {
		return roundScheduleId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTripType(String tripType) {
		this.tripType = tripType;
	}

	public void setPassengersList(List<PassengerRequest> passengersList) {
		this.passengersList = passengersList;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public void setBillingAmmount(double billingAmmount) {
		this.billingAmmount = billingAmmount;
	}

	public void setOnwardScheduleId(Long onwardScheduleId) {
		this.onwardScheduleId = onwardScheduleId;
	}

	public void setRoundScheduleId(Long roundScheduleId) {
		this.roundScheduleId = roundScheduleId;
	}

	@Override
	public String toString() {
		return "BookingRequest [name=" + name + ", email=" + email + ", tripType=" + tripType + ", passengersList="
				+ passengersList + ", discount=" + discount + ", billingAmmount=" + billingAmmount
				+ ", onwardScheduleId=" + onwardScheduleId + ", roundScheduleId=" + roundScheduleId + "]";
	}
    
}
