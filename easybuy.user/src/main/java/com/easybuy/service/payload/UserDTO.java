package com.easybuy.service.payload;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserDTO {
	private int userId;
	private String userName;
	private String userEmail;
	private String password;
	private long mobileNumber;
	private String userAddress;
}
