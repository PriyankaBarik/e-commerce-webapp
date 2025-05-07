package com.pb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pb.dto.order.OrderDto;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

@Service
public class OrderService {

	
	@Value("${BASE_URL}")
	private String baseUrl;

	@Value("${STRIPE_SECRET_KEY}")
	private String apiKey;

	public Session CreateSession(List<OrderDto> orderDtoToList) throws StripeException{

		String successUrl = baseUrl + "payment/success";
		String failureURL = baseUrl + "payment/failed";

		Stripe.apiKey = apiKey;
		List<SessionCreateParams.LineItem> sessionnItemList = new ArrayList<>();
		for (OrderDto orderDto : orderDtoToList) {
			sessionnItemList.add(CreateSessionLineItem(orderDto));
		}

		SessionCreateParams parms=SessionCreateParams.builder()
				.addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
				.setMode(SessionCreateParams.Mode.PAYMENT)
				.setCancelUrl(failureURL)
				.setSuccessUrl(successUrl)
				.addAllLineItem(sessionnItemList)
				.build();
		return Session.create(parms);
	}

	private SessionCreateParams.LineItem CreateSessionLineItem(OrderDto orderDto) {

		return SessionCreateParams.LineItem.builder().setPriceData(CreatePriceData(orderDto))
				.setQuantity(Long.parseLong(String.valueOf(orderDto.getQuentity()))).build();

	}

	private SessionCreateParams.LineItem.PriceData CreatePriceData(OrderDto orderDto) {

		return SessionCreateParams.LineItem.PriceData.builder()
				.setCurrency("usd")
				.setUnitAmount((long) (orderDto.getPrice() * 100))
				.setProductData(
						SessionCreateParams.LineItem.PriceData.ProductData.builder()
				             .setName(orderDto.getProductName())
				             .build())
				.build();
	}

}
