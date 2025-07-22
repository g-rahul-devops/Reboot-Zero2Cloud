package com.reboot.zerotocloud.controller;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reboot.zerotocloud.dto.MonitoringRequest;
import com.reboot.zerotocloud.dto.MonitoringResponse;
import com.reboot.zerotocloud.service.GCPMonitoringService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/cloudsql")
@Validated
public class CloudSqlController {

    private final GCPMonitoringService service;

    public CloudSqlController(GCPMonitoringService service) {
        this.service = service;
    }

    @PostMapping(path = "/cpu", produces = MediaType.APPLICATION_JSON_VALUE)
    public MonitoringResponse getCpu(
        @RequestBody @Valid MonitoringRequest req
    ) throws IOException {
        return service.getCloudSqlCpu(req);
    }

    @PostMapping(path = "/connections", produces = MediaType.APPLICATION_JSON_VALUE)
    public MonitoringResponse getConnections(
        @RequestBody @Valid MonitoringRequest req
    ) throws IOException {
        return service.getCloudSqlConnections(req);
    }
}

