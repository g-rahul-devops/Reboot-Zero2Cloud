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
@RequestMapping("/api/v1/bucket")
@Validated
public class BucketController {

    private final GCPMonitoringService service;

    public BucketController(GCPMonitoringService service) {
        this.service = service;
    }

    @PostMapping(path = "/bytes", produces = MediaType.APPLICATION_JSON_VALUE)
    public MonitoringResponse getTotalBytes(
        @RequestBody @Valid MonitoringRequest req
    ) throws IOException {
        return service.getBucketBytes(req);
    }

    @PostMapping(path = "/objects", produces = MediaType.APPLICATION_JSON_VALUE)
    public MonitoringResponse getObjectCount(
        @RequestBody @Valid MonitoringRequest req
    ) throws IOException {
        return service.getBucketObjectCount(req);
    }
}

