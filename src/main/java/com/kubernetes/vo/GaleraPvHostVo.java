package com.kubernetes.vo;

import java.util.Map;

public class GaleraPvHostVo {

	private String apiVersion;
	private String kind;
	private Map<String, Object> metadata;
	private Map<String, Object> spec;
	
	public String getApiVersion() {
		return apiVersion;
	}
	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public Map<String, Object> getMetadata() {
		return metadata;
	}
	public void setMetadata(Map<String, Object> metadata) {
		this.metadata = metadata;
	}
	public Map<String, Object> getSpec() {
		return spec;
	}
	public void setSpec(Map<String, Object> spec) {
		this.spec = spec;
	}
	
}
