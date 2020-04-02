package com.anz.ws.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.stereotype.Service;

@Service
public  class LoadDeveloperTokenService {
	
	public String FetchToken() throws IOException
	{
		Properties pro = new Properties();
		InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
		if (input != null) {
			pro.load(input);
		} else {
			throw new FileNotFoundException("Properties file does not exist in the classpath");
		}
		
		String token=pro.getProperty("DEVELOPER_TOKEN");
		
		return token;
	}

}
