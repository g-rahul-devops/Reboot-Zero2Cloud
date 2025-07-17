package com.reboot.zerotocloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reboot.zerotocloud.config.GCPConfig;
import com.reboot.zerotocloud.service.MonitoringService;

@RestController
@RequestMapping("/monitoring")
public class MonitoringController {
	
	@Value("${gcp.key.path}")
    private String keyPath;

	
	 private final MonitoringService monitoringService;

	    public MonitoringController(MonitoringService monitoringService) {
	        this.monitoringService = monitoringService;
	    }

	    @GetMapping("/{resourceType}")
	    public String getMetrics(
	            @PathVariable String resourceType,
	            @RequestParam String projectId
	    ) throws Exception {
	        var credentials = GCPConfig.getCredentials(keyPath);
	        return monitoringService.fetchMetrics(projectId, resourceType, credentials);
	    }


}
