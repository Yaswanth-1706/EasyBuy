package com.easybuy.service.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDTO {
	private int  cartId;
	private int userId;
	private int productId;

}
