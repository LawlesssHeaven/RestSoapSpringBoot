package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;


@Configuration
public class OrderConfiguration {
    private static final Logger log = LoggerFactory.getLogger(GreetingController.class);

	@Bean
	public Jaxb2Marshaller marshaller() {
		log.info("Enters Jaxb2Marshaller will set ContextPath");

		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml 
		marshaller.setContextPath("org.example.imaginaryoutlet");
		return marshaller;
	}

	@Bean
	public OrderClient orderClient(Jaxb2Marshaller marshaller) {
		log.info("Enters OrderClient will make new OrderClient");

		OrderClient client = new OrderClient();
		client.setDefaultUri("http://localhost:8081/ws/");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		log.info("OrderClient will return new client");
		log.info(client.toString());
		return client;
	}
}