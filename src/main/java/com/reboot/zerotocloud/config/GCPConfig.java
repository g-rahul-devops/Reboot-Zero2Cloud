//package com.reboot.zerotocloud.config;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.client.RestTemplate;
//
//import com.google.auth.oauth2.ServiceAccountCredentials;
//
//
//@Configuration
//public class GCPConfig {
//
//    @Value("${gcp.key.path}")
//    private String gcpKeyPath;
//
//
////    public static HttpCredentialsAdapter getCredentials(String keyPath) throws IOException {
////        GoogleCredentials credentials = GoogleCredentials
////                .fromStream(new FileInputStream(keyPath))
////                .createScoped(Collections.singleton("https://www.googleapis.com/auth/cloud-platform"));
////        return new HttpCredentialsAdapter(credentials);
////    }
//
//    @Bean
//    ServiceAccountCredentials gcpCredentials() throws IOException {
//        try (FileInputStream stream = new FileInputStream(gcpKeyPath)) {
//            return (ServiceAccountCredentials) ServiceAccountCredentials.fromStream(stream)
//            		.createScoped("https://www.googleapis.com/auth/cloud-platform");
////                .createScoped("https://www.googleapis.com/auth/monitoring.read");
//        }
//    }
//
//    @Bean
//    RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
//
//
//}
