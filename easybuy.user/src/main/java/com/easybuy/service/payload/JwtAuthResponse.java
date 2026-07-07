package com.easybuy.service.payload;

import lombok.Getter;

@Getter
public class JwtAuthResponse {
	private String token;
	private String tokenType="Bearer";
	private UserLoginDTO udto;
	
	public JwtAuthResponse(String token,UserLoginDTO udto) {
		this.token=token;	
		this.udto=udto;
	}

}
