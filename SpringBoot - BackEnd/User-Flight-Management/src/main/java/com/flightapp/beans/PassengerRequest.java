package com.flightapp.beans;

public class PassengerRequest {

	private String pName;
    private String pGender;
    private Integer age;
    private String onwardSeat;
    private String returnSeat;
    private String optMeal;
    
    public PassengerRequest() {}
    
	public PassengerRequest(String pName, String pGender, Integer age, String onwardSeat, String returnSeat,
			String optMeal) {
		super();
		this.pName = pName;
		this.pGender = pGender;
		this.age = age;
		this.onwardSeat = onwardSeat;
		this.returnSeat = returnSeat;
		this.optMeal = optMeal;
	}
	
	public String getpName() {
		return pName;
	}
	public String getpGender() {
		return pGender;
	}
	public Integer getAge() {
		return age;
	}
	public String getOnwardSeat() {
		return onwardSeat;
	}
	public String getReturnSeat() {
		return returnSeat;
	}
	public String getOptMeal() {
		return optMeal;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public void setpGender(String pGender) {
		this.pGender = pGender;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public void setOnwardSeat(String onwardSeat) {
		this.onwardSeat = onwardSeat;
	}
	public void setReturnSeat(String returnSeat) {
		this.returnSeat = returnSeat;
	}
	public void setOptMeal(String optMeal) {
		this.optMeal = optMeal;
	}
	
	
	@Override
	public String toString() {
		return "PassengerRequest [pName=" + pName + ", pGender=" + pGender + ", age=" + age + ", onwardSeat="
				+ onwardSeat + ", returnSeat=" + returnSeat + ", optMeal=" + optMeal + "]";
	}


    
}
