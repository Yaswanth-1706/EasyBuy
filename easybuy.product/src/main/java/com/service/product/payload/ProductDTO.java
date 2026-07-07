package com.service.product.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
	private String productName;
	private String productImage;
	private String productDescription;
	private long price;
	private int quantity;
	private String stock;
}
