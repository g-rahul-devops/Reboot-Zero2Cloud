package com.reboot.zerotocloud.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.http.HttpCredentialsAdapter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;

public class GCPConfig {
	
    public static HttpCredentialsAdapter getCredentials(String keyPath) throws IOException {
        GoogleCredentials credentials = GoogleCredentials
                .fromStream(new FileInputStream(keyPath))
                .createScoped(Collections.singleton("https://www.googleapis.com/auth/cloud-platform"));
        return new HttpCredentialsAdapter(credentials);
    }

}
