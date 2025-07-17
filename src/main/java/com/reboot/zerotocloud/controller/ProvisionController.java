package com.reboot.zerotocloud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reboot.zerotocloud.model.VMProvisionCriteria;

@RestController
@RequestMapping("/provisioning") 
public class ProvisionController {
	
	@PostMapping("/vm") //Sample API to test connectivity from UI
	public ResponseEntity<String> provisionVM(@RequestBody VMProvisionCriteria vmprovision) {
		
		return ResponseEntity.ok("VM is provisioned successfully");
		
	}

}
