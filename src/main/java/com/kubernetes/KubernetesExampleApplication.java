package com.kubernetes;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1Pod;
import io.kubernetes.client.models.V1PodList;
import io.kubernetes.client.util.Config;

@SpringBootApplication
public class KubernetesExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(KubernetesExampleApplication.class, args);
		
		ApiClient client;
		try {
			client = Config.defaultClient();
	        Configuration.setDefaultApiClient(client);
	
	        CoreV1Api api = new CoreV1Api();
	        V1PodList list = api.listPodForAllNamespaces(null, null, null, null, null, null);
	        for (V1Pod item : list.getItems()) {
	            System.out.println(item.getMetadata().getName());
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
