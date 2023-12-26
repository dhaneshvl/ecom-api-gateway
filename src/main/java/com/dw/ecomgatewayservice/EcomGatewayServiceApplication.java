package com.dw.ecomgatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EcomGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomGatewayServiceApplication.class, args);
	}

}
