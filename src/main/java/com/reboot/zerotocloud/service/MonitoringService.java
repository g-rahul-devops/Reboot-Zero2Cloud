//package com.reboot.zerotocloud.service;
//
//import org.springframework.stereotype.Service;
//
//import com.google.api.client.http.GenericUrl;
//import com.google.api.client.http.HttpRequest;
//import com.google.api.client.http.HttpRequestFactory;
//import com.google.api.client.http.HttpRequestInitializer;
//import com.google.api.client.http.HttpResponse;
//import com.google.api.client.http.HttpTransport;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.JsonFactory;
//
//@Service
//public class MonitoringService {
//
//	private static final String MONITORING_BASE = "https://monitoring.googleapis.com/v3/projects/";
//
//	private final HttpTransport httpTransport = new NetHttpTransport();
//	private final JsonFactory jsonFactory = new com.google.api.client.json.gson.GsonFactory();
//
//	public String fetchMetrics(String projectId, String resourceType, HttpRequestInitializer requestInitializer)
//			throws Exception {
//		String filter = switch (resourceType) {
//		case "vm" -> "metric.type=\"compute.googleapis.com/instance/cpu/utilization\"";
//		case "bucket" -> "metric.type=\"storage.googleapis.com/storage/object_count\"";
//		case "vpc" -> "metric.type=\"compute.googleapis.com/network/received_bytes_count\"";
//		default -> throw new IllegalArgumentException("Unsupported type");
//		};
//		String timeinterval = String.format("&interval.startTime=%s&interval.endTime=%s", "2025-07-20T08:00:00Z", "2025-07-21T09:00:00Z");
//
//		String url = MONITORING_BASE + projectId + "/timeSeries?filter=" + filter + timeinterval;
//
//		HttpRequestFactory requestFactory = httpTransport.createRequestFactory(requestInitializer);
//		HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(url));
//		HttpResponse response = request.execute();
//
//		return response.parseAsString();
//	}
//}