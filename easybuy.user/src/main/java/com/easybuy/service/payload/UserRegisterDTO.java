package com.easybuy.service.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegisterDTO {
	private int userId;
	private String userName;
	private String userEmail;
	private String password;
	private long mobileNumber;
	private String userAddress;
}
