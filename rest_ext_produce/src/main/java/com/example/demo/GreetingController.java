package com.example.demo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import org.example.imaginaryoutlet.GetOrderRequest;
import org.example.imaginaryoutlet.GetOrderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class GreetingController {

    private static final String template = "Hello darkness , %s!";
    private final AtomicLong counter = new AtomicLong();
    private static final Logger log = LoggerFactory.getLogger(GreetingController.class);

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
	
	
	@RequestMapping("/shop")
    public Item shopping(@RequestParam(value="id", defaultValue="1") String id,@RequestParam(value="price", defaultValue="250") Double price) {
        return new Item(id,price);
    }

	
		@RequestMapping("/warehouse")
    public OrderStatus warehouse(@RequestParam(value="id", defaultValue="1") String id,
    							@RequestParam(value="price",defaultValue="250") Double price,	
    							@RequestParam(value="newItem",defaultValue="true") Boolean newItem ) {
		
			if(newItem == false) {
				log.error("So here I am just tried to redirect request to SOAP");
				
				log.error("Items ID: " + id);
				log.error("Items price: " + price);
				log.error("Items status: " + newItem);
				OrderConfiguration orderConf = new OrderConfiguration();
				OrderClient client = orderConf.orderClient(orderConf.marshaller());
//				client.setMarshaller(orderConf.marshaller());
//				client.setUnmarshaller(orderConf.marshaller());


				
				GetOrderRequest order = new GetOrderRequest();
				order.setId(id);
				order.setPrice(price.intValue());
				order.setNewItem(newItem);
				log.error("So checking get order request from GreetingController"+" Orders ID: "+order.getId()+ "Orders price: "+order.getPrice()+ " Condition of Item isNew ?"+ order.isNewItem());
				GetOrderResponse response = client.getStatus(order);
				
				OrderStatus myStatus = new OrderStatus();
				myStatus.setStatus(response.getStatus().getStatus());
				myStatus.setWarehouse(response.getStatus().getWarehouse());
				
				
				
				return myStatus;
				
			} else {
				log.info("Id of our Order: "+ id);
				log.info("Price of Order: "+ price);
				log.info("Is item new ? "+ newItem);
				log.info("Everyting went grate and I produced Order using REST");
				OrderStatus result = new OrderStatus(price);
				
				log.info("Getting orders status: " + result.getStatus());
				log.info("Getting orders warehouse: " + result.getWarehouse());
				
		        return result;

			}
			
			
    }


}