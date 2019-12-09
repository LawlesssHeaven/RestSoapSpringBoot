package com.example.demo;


import org.example.imaginaryoutlet.GetOrderRequest;
import org.example.imaginaryoutlet.GetOrderResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import org.example.imaginaryoutlet.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Endpoint
public class OrderEndpoint {
	private static final String NAMESPACE_URI = "http://www.example.org/ImaginaryOutlet";
	private static final Logger log = LoggerFactory.getLogger(OrderEndpoint.class);

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOrderRequest")
	@ResponsePayload
	public GetOrderResponse getStatus(@RequestPayload GetOrderRequest request) {
		log.info("Requesting status for " + request.toString());

		GetOrderResponse response = new GetOrderResponse();
		OrderStatus status = new OrderStatus();
		
		log.info("Getting ID of request: " + request.getId());
		log.info("Getting price of request: " + request.getPrice());
		
		String LINK = "http://localhost:8082/warehouse?price="+request.getPrice();

		if(request.isNewItem() == true) {
			log.info("Damn I couldn't do anything so I'll pass it further to Rest so he can deal with it");
			RestTemplateBuilder builder = new RestTemplateBuilder();
			builder.build();
			
			RestTemplate restTemplate = new RestTemplate();
			
			
			status =restTemplate.getForObject(LINK, OrderStatus.class);

			response.setStatus(status);
			log.info("Getting orders status: " + response.getStatus().getStatus());
			log.info("Getting orders warehouse: " + response.getStatus().getWarehouse());
			log.info("Ayee everything went grate and REST dealt with NEW ORDER");

			return response;

		}else {
			log.info("Ayee everyting went grate and SAOP dealt with USED ORDER");

			status.setStatus(request.getPrice());
			response.setStatus(status);
			log.info("Getting orders status: " + response.getStatus().getStatus());
			log.info("Getting orders warehouse: " + response.getStatus().getWarehouse());
			return response;
		}

	}
}
