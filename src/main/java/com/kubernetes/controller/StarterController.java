package com.kubernetes.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kubernetes.service.YamlParsingService;

import io.kubernetes.client.ApiException;

@Controller
public class StarterController {

	@Autowired
	private YamlParsingService service;
	
	@RequestMapping("/")
	public void index() throws IOException, ApiException{
		service.createUserToken();
	}
	
	@RequestMapping("/createPersistentVolume")
	public void createPersistentVolume() throws IOException, ApiException{
        service.createPersistentVolume();
	}
	
	@RequestMapping("/createSecret")
	public void createSecret() throws IOException, ApiException{
        service.createSecret();
	}
	
	@RequestMapping("/createService")
	public void createService() throws IOException, ApiException{
        service.createService();
	}
}
