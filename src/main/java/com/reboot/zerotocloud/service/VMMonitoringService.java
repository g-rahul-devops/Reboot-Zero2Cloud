//package com.reboot.zerotocloud.service;
//
//import org.springframework.stereotype.Service;
//
//import com.reboot.zerotocloud.util.GcpMonitoringClient;
//
//@Service
//public class VMMonitoringService {
//
//    private final GcpMonitoringClient client;
//
//    public VMMonitoringService(GcpMonitoringClient client) {
//        this.client = client;
//    }
//
//    public String getCpuUsage(String startTime, String endTime, String instanceId) throws Exception {
//        String metricType = "compute.googleapis.com/instance/cpu/usage_time";
//        String filter = String.format("resource.label.instance_id=\"%s\"", instanceId);
//        return client.fetchMetric(metricType, startTime, endTime, filter);
//    }
//
//    public String getNetworkIn(String startTime, String endTime, String instanceId) throws Exception {
//        String metricType = "compute.googleapis.com/instance/network/received_bytes_count";
//        String filter = String.format("resource.label.instance_id=\"%s\"", instanceId);
//        return client.fetchMetric(metricType, startTime, endTime, filter);
//    }
//}
//
