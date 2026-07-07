package com.easybuy.service.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginDTO {
	private String userId;
	private String userEmail;
	private String password ;

}
