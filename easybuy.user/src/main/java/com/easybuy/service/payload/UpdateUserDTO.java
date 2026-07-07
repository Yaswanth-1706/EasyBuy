package com.easybuy.service.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDTO {
	private String userName;
	private String userEmail;
	private long mobileNumber;
	private String userAddress;

}
