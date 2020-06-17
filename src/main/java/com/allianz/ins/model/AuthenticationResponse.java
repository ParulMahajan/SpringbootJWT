package com.allianz.ins.model;

public class AuthenticationResponse {

	private  String jwtToken;

	public AuthenticationResponse() {
		
	}
	
	public AuthenticationResponse(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	public String getJwtToken() {
		return jwtToken;
	}
	
	
}
