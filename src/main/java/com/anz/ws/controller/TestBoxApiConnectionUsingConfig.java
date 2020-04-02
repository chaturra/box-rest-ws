package com.anz.ws.controller;

import com.box.sdk.BoxDeveloperEditionAPIConnection;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

import com.box.sdk.*;

public class TestBoxApiConnectionUsingConfig {
	private static final String USER_ID = "12311385367";
	private static final int MAX_DEPTH = 1;
	private static final int MAX_CACHE_ENTRIES = 100;


	private TestBoxApiConnectionUsingConfig() {
	};

	public static void main(String[] args) throws IOException {
		IAccessTokenCache accessTokenCache = new InMemoryLRUAccessTokenCache(MAX_CACHE_ENTRIES);
		Resource resource = new ClassPathResource("config.json");
		File file = resource.getFile();
		System.out.println(file.getAbsolutePath());
		Reader reader = new FileReader(file);
		System.out.println(reader.toString());
		BoxConfig boxConfig = BoxConfig.readFrom(reader);
		System.out.println("BoxConfig:"+boxConfig.getClientId());
		BoxDeveloperEditionAPIConnection api = BoxDeveloperEditionAPIConnection.getAppEnterpriseConnection(boxConfig);

	}

}
