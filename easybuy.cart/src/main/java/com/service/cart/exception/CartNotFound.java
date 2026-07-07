package com.service.cart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class CartNotFound extends RuntimeException{
public CartNotFound(String message) {
	super(message);
}
}
