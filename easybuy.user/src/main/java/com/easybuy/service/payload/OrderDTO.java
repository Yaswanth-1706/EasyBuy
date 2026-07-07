package com.easybuy.service.payload;

import lombok.Data;

@Data
public class OrderDTO {
private int orderId;
private int userId;
private int productId;
private String productOrderId;
}
