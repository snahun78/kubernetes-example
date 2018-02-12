package com.kubernetes.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import io.kubernetes.client.models.V1HostPathVolumeSource;
import io.kubernetes.client.models.V1ObjectMeta;
import io.kubernetes.client.models.V1PersistentVolumeSpec;

public class YamlParsingUtil {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static V1ObjectMeta getV1ObjectMetaFromMap(Map map) {
		Iterator innerKeyIter = map.keySet().iterator();
		V1ObjectMeta meta = new V1ObjectMeta();
		
		while(innerKeyIter.hasNext()) {
			String key = (String)innerKeyIter.next();
			Object value = map.get(key);
			
			if("annotations".equals(key)) {
				meta.setAnnotations((Map<String, String>)value);
			}
			else if("name".equals(key)) {
				meta.setName((String)value);
			}
			else if("labels".equals(key)) {
				meta.setLabels((Map<String, String>)value);
			}
		}
		
		return meta;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static V1PersistentVolumeSpec getV1PersistentVolumeSpecFromMap(Map map) {
		Iterator innerKeyIter = map.keySet().iterator();
		V1PersistentVolumeSpec spec = new V1PersistentVolumeSpec();
		
		while(innerKeyIter.hasNext()) {
			String key = (String)innerKeyIter.next();
			Object value = map.get(key);
			
			if("accessModes".equals(key)) {
				spec.setAccessModes((List<String>)value);
			}
			else if("capacity".equals(key)) {
				spec.setCapacity((Map<String, String>)value);
			}
			else if("hostPath".equals(key)) {
				V1HostPathVolumeSource hpvs = new V1HostPathVolumeSource();
				hpvs.setPath((String)((Map)value).get("path"));
				spec.setHostPath(hpvs);
			}
		}
		
		return spec;
	}
	
}
