package com.dw.ecomgatewayservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallBackController {

    @GetMapping("/order-service")
    public ResponseEntity<String> orderServiceFallback() {
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("We apologize " +
                "for any inconvenience. Currently, we are experiencing technical " +
                "issues with our order service. Please reach out to our customer " +
                "support for assistance. Thank you for your understanding.");
    }

    @GetMapping("/payment-service")
    public ResponseEntity<String> paymentServiceFallback() {
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("We apologize " +
                "for any inconvenience. Currently, we are experiencing technical " +
                "issues with our payment service. Please reach out to our customer " +
                "support for assistance. Thank you for your understanding.");
    }

    @GetMapping("/user-service")
    public ResponseEntity<String> userServiceFallback() {
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("We apologize " +
                "for any inconvenience. Currently, we are experiencing technical " +
                "issues with our user service. Please reach out to our customer " +
                "support for assistance. Thank you for your understanding.");
    }

}
