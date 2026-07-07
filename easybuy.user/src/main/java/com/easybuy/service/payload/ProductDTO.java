package com.easybuy.service.payload;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
	private int productId;
	private String productName;
	  private String productImage;
	  private String productDescription;
	  private long price;
	  private int quantity;
	  private String stock;
	  private CategoryDTO category;
	  private String categoryName;

}
