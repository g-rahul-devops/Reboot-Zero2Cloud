package com.reboot.zerotocloud.controller;

import com.reboot.zerotocloud.model.BillingInfo;
import com.reboot.zerotocloud.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gcp/billing")
public class BillingController {

    @Autowired
    private BillingService billingService;

    @GetMapping("/summary")
    public ResponseEntity<BillingInfo> getBillingSummary() {
        try {
            BillingInfo info = billingService.fetchBillingData();
            return ResponseEntity.ok(info);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
