package com.pb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pb.dto.order.OrderDto;
import com.pb.dto.order.StripeResponse;
import com.pb.service.AuthenticationTokenService;
import com.pb.service.OrderService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;


@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	AuthenticationTokenService authenticationService;
	
	@Autowired
	OrderService orderService;
	
	//checkOut Api
	@PostMapping("/create-checkout-session")
	public ResponseEntity<StripeResponse> checkoutList(@RequestBody List<OrderDto>  orderDto ) throws StripeException{
		Session session= orderService.CreateSession(orderDto);
		StripeResponse str=new StripeResponse(session.getId());
		
		return new ResponseEntity<StripeResponse>(str,HttpStatus.OK);
	}
	
}
