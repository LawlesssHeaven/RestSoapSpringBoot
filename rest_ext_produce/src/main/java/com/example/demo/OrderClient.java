package com.example.demo;

import org.example.imaginaryoutlet.GetOrderRequest;
import org.example.imaginaryoutlet.GetOrderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class OrderClient extends WebServiceGatewaySupport {
	private static final Logger log = LoggerFactory.getLogger(OrderClient.class);

	public GetOrderResponse getStatus(GetOrderRequest request) {


		log.error("Getting request from OrderClient "+ "Order ID: "+request.getId() +" Order Price: "+ request.getPrice());
		
		
		GetOrderResponse response = (GetOrderResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8081/ws/", request,
						new SoapActionCallback(
								"http://spring.io/guides/gs-producing-web-service/GetOrderRequest"));
		
		log.error("Getting response from OrderClient"+response.getStatus().getStatus());
		return response;
	}
}
