package com.reboot.zerotocloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
public class TerraformController {

    @GetMapping("/create-bucket")
    public String createBucket() throws IOException, InterruptedException {
        String terraformDir = "/app/terraform";

        // Copy credentials JSON (done during Docker build, optional here)
        runCommand("terraform init", terraformDir);
        runCommand("terraform apply -auto-approve", terraformDir);

        return "Bucket creation triggered!";
    }

    private void runCommand(String command, String dirPath) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder("sh", "-c", command);
        builder.directory(new File(dirPath));
        builder.inheritIO();
        Process process = builder.start();
        int code = process.waitFor();
        if (code != 0) throw new RuntimeException("Terraform failed: " + command);
    }
}
