package com.kubernetes.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.kubernetes.vo.GaleraPvHostVo;

import io.kubernetes.client.models.V1PersistentVolume;

public class YamlLoaderUtil{

	private static String FILE_BASE_FOLDER = "/templates/";
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<Map<String, Object>> fileLoadAsMap(String fileName) {
		Yaml yaml = new Yaml();
		List<Map<String, Object>> resultList = new ArrayList<>();
		
		try {
			InputStream in = YamlLoaderUtil.class.getResourceAsStream(FILE_BASE_FOLDER + fileName);
			Iterable<Object> itr = yaml.loadAll(in);
			
			for(Object obj : itr) {
				if(obj instanceof HashMap) {
					resultList.add((HashMap)obj);
				}
			}
			
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return resultList;
	}

	@SuppressWarnings("unused")
	public static List<GaleraPvHostVo> fileLoadAsObj(String fileName) {
		Yaml yaml = new Yaml();
		List<GaleraPvHostVo> resultList = new ArrayList<>();
		
		try {
			InputStream in = YamlLoaderUtil.class.getResourceAsStream(FILE_BASE_FOLDER + fileName);
			Object ga = yaml.loadAs(in, GaleraPvHostVo.class);
			
//			resultList.add(ga);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return resultList;
	}
	
	@SuppressWarnings({ "rawtypes", "serial" })
	public static List fileLoadAsListObj(String fileName){
		
		List<Map<String, Object>> yamlListMap = fileLoadAsMap(fileName);
		
		Gson gson = new Gson();
        String jsonStr = gson.toJson(yamlListMap);
        List<?> convertResult = gson.fromJson(jsonStr, new TypeToken<List<V1PersistentVolume>>(){}.getType());
        System.out.println(convertResult);
        
		return convertResult;
	}
}
