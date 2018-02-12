package com.kubernetes.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kubernetes.util.YamlLoaderUtil;

import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.AuthenticationV1Api;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.auth.ApiKeyAuth;
import io.kubernetes.client.models.V1NamespaceList;
import io.kubernetes.client.models.V1PersistentVolume;
import io.kubernetes.client.models.V1Secret;
import io.kubernetes.client.models.V1TokenReview;
import io.kubernetes.client.models.V1TokenReviewStatus;
import io.kubernetes.client.models.V1UserInfo;
import io.kubernetes.client.util.Config;

@Service
public class YamlParsingService {
	
	private String pretty = "pretty";
	
	@SuppressWarnings("unused")
	public void createUserToken() throws IOException {
		ApiClient client = Config.defaultClient();					// Http
//		ApiClient client = Configuration.getDefaultApiClient();		// Https
		
        Configuration.setDefaultApiClient(client);
        ApiKeyAuth BearerToken = (ApiKeyAuth) client.getAuthentication("BearerToken");
		client.setApiKeyPrefix("Token");
		
		AuthenticationV1Api apiInstance = new AuthenticationV1Api();
		V1TokenReview authBody = new V1TokenReview();
		V1UserInfo user = new V1UserInfo();
		user.setUsername("default-token-7rh9c");
		V1TokenReviewStatus status = new V1TokenReviewStatus();
		status.setUser(user);
		authBody.setStatus(status);
		String pretty = "pretty";
		try {
			V1TokenReview result = apiInstance.createTokenReview(authBody, pretty);
            System.out.println(result);
        }
        catch (Exception e) {
        	e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "unchecked"})
	public void createPersistentVolume() throws IOException {
		
		String fileName = "galera-pv-host.yaml";
		List<V1PersistentVolume> yamlList = YamlLoaderUtil.fileLoadAsListObj(fileName);
		
		ApiClient client = Config.defaultClient();					// Http
        Configuration.setDefaultApiClient(client);
        
        CoreV1Api coreV1Api = new CoreV1Api();
		for (V1PersistentVolume body : yamlList) {
			
			try {
				V1PersistentVolume result = coreV1Api.createPersistentVolume(body, pretty);
				System.out.println(result);
			} catch (ApiException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public void createSecret() throws IOException {
		String fileName = "secrets-development.yaml";
		List<V1Secret> yamlList = YamlLoaderUtil.fileLoadAsListObj(fileName);
		ApiClient client = Config.defaultClient();					// Http
		Configuration.setDefaultApiClient(client);
		
		CoreV1Api coreV1Api = new CoreV1Api();
		try {
			String _continue = "_continue_example";
			String fieldSelector = "fieldSelector_example";
			String labelSelector = "labelSelector_example";
			Integer limit = 56;
			Boolean watch = true;
			V1NamespaceList result1 = coreV1Api.listNamespace(pretty, _continue, fieldSelector, labelSelector, limit, watch);
			System.out.println(result1);
			for (V1Secret body : yamlList) {
//				V1PersistentVolume result = coreV1Api.createNamespacedSecret(namespace, body, fileName);
//				System.out.println(result);
			}
		} catch (ApiException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public void createService() throws IOException {
		String fileName = "galera.yaml";
		
		List<V1PersistentVolume> yamlList = YamlLoaderUtil.fileLoadAsListObj(fileName);
	}
}
