package com.allianz.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allianz.shopping.model.OrderDetailsInfo;
import com.allianz.shopping.model.OrderResponse;
import com.allianz.shopping.service.ShoppingService;

@RestController
@RequestMapping("/shopping")
public class ShoppingController {

	@Autowired
	private ShoppingService shoppingService;

	@PostMapping(path = "order", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderResponse> submitOrder(@RequestBody OrderDetailsInfo orderedItemsInfo) throws Exception {
		OrderResponse orderResponse = shoppingService.orderShopping(orderedItemsInfo);
		return new ResponseEntity<>(orderResponse, HttpStatus.OK);
	}

}
