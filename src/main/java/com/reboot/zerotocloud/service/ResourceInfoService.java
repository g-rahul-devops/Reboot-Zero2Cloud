package com.reboot.zerotocloud.service;

import com.google.cloud.compute.v1.*;
import com.reboot.zerotocloud.model.GcpVmInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class ResourceInfoService {

    @Value("${gcp.project-id}")
    private String projectId;

    public ResponseEntity<List<GcpVmInfo>> getAllVmInfo() throws IOException {
        List<GcpVmInfo> result = new ArrayList<>();

        try (InstancesClient instancesClient = InstancesClient.create();
             DisksClient disksClient = DisksClient.create()) {

            AggregatedListInstancesRequest request = AggregatedListInstancesRequest.newBuilder()
                    .setProject(projectId)
                    .build();

            for (Map.Entry<String, InstancesScopedList> entry : instancesClient.aggregatedList(request).iterateAll()) {
                if (entry.getValue().getInstancesList() != null) {
                    for (Instance instance : entry.getValue().getInstancesList()) {

                        String zone = extractLast(instance.getZone());
                        String machineType = extractLast(instance.getMachineType());
                        String instanceName = instance.getName();
                        String createdTime = instance.getCreationTimestamp();
                        String status = instance.getStatus();

                        String sourceImage = "unknown";

                        try {
                            String diskUrl = instance.getDisks(0).getSource(); // full path
                            String diskName = extractLast(diskUrl);

                            Disk disk = disksClient.get(projectId, zone, diskName);
                            if (disk.hasSourceImage()) {
                                sourceImage = disk.getSourceImage(); // full image path
                            }
                        } catch (Exception e) {
                            // optional: log warning or skip
                            sourceImage = "unavailable";
                        }

                        GcpVmInfo vmInfo = new GcpVmInfo(
                                instanceName,
                                status,
                                machineType,
                                zone,
                                sourceImage,
                                createdTime,
                                null // memory usage not fetched yet
                        );

                        result.add(vmInfo);
                    }
                }
            }
        }

        return ResponseEntity.ok(result);
    }

    private String extractLast(String fullPath) {
        return fullPath.substring(fullPath.lastIndexOf("/") + 1);
    }
}
