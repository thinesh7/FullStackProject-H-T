package com.flightapp.auth;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;

	private String jwttoken;
	private String userName;
	
	public JwtResponse() {
	}

	public JwtResponse(String jwttoken,String name) {
		this.jwttoken = jwttoken;
		this.userName = name;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
