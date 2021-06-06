package com.actuator.controller.endpoint;

import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@RestControllerEndpoint(id = "custom-controller-endpoint")
public class CustomControllerEndpoint {

	
	@GetMapping()
	public ResponseEntity<String> readEndpoint() {
		return ResponseEntity.ok("This is the custom actuator endpoint using controller");
	}
}
