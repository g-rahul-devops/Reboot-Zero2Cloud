package com.reboot.zerotocloud.service;

import com.google.cloud.compute.v1.Instance;
import com.google.cloud.compute.v1.InstancesClient;
import com.google.cloud.compute.v1.InsertInstanceRequest;
import com.google.cloud.compute.v1.Operation;
import com.google.cloud.compute.v1.AttachedDisk;
import com.google.cloud.compute.v1.NetworkInterface;
import com.google.cloud.compute.v1.AttachedDiskInitializeParams;
import com.reboot.zerotocloud.model.VMProvisionCriteria;
import org.springframework.stereotype.Service;
import com.google.cloud.compute.v1.DeleteInstanceRequest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
public class GCPProvisionService {

    public Operation provisionVM(VMProvisionCriteria criteria) throws IOException, ExecutionException, InterruptedException {
        try (InstancesClient instancesClient = InstancesClient.create()) {
            Instance instanceResource = Instance.newBuilder()
                    .setName(criteria.getInstanceName())
                    .setMachineType(String.format("zones/%s/machineTypes/%s",
                            criteria.getZone(),
                            criteria.getMachineType()))
                    .addNetworkInterfaces(NetworkInterface.newBuilder()
                            .setName("default")
                            .build())
                    .addDisks(AttachedDisk.newBuilder()
                            .setInitializeParams(AttachedDiskInitializeParams.newBuilder()
                                    .setSourceImage("projects/debian-cloud/global/images/debian-11-bullseye-v20240213")
                                    .build())
                            .setAutoDelete(true)
                            .setBoot(true)
                            .setType("PERSISTENT")
                            .build())
                    .build();

            InsertInstanceRequest request = InsertInstanceRequest.newBuilder()
                    .setProject(criteria.getProjectId())
                    .setZone(criteria.getZone())
                    .setInstanceResource(instanceResource)
                    .build();

            return instancesClient.insertAsync(request).get();
        }
    }


    public Operation deleteVM(String project, String zone, String instanceName) throws IOException, InterruptedException, ExecutionException {
        try (InstancesClient instancesClient = InstancesClient.create()) {
            DeleteInstanceRequest deleteInstanceRequest = DeleteInstanceRequest.newBuilder()
                    .setProject(project)
                    .setZone(zone)
                    .setInstance(instanceName)
                    .build();

            return instancesClient.deleteAsync(deleteInstanceRequest).get();
        }
    }

}
