package com.anz.ws.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anz.ws.request.model.ServiceRequest;
import com.anz.ws.response.model.CreateUserApiResponse;
import com.anz.ws.response.model.ServiceResponse;
import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxUser;

@Service
public class CreateAppUserService {

	@Autowired
	LoadDeveloperTokenService loadToken;

	public CreateUserApiResponse CreateUser(ServiceRequest req) throws IOException {
		String accessToken = loadToken.FetchToken();
		CreateUserApiResponse res= new CreateUserApiResponse();
		System.out.println("accessToken: "+accessToken);

		BoxAPIConnection api = new BoxAPIConnection(accessToken);

		String userName = req.getUserName();
		String userLogin = req.getUserLogin();
		System.out.println("user: "+userName);
		System.out.println("login: "+userLogin);

		BoxUser.Info createdUserInfo = BoxUser.createEnterpriseUser(api, userLogin, userName);
		System.out.println("API Response: "+createdUserInfo.getStatus());
		if (null != createdUserInfo && null!=createdUserInfo.getID()) {
			res.setStatus("201");
			res.setMessage("successfully created");
			res.setUserId(createdUserInfo.getID());
		}
		else {
			res.setStatus(createdUserInfo.getStatus().toString());
			res.setMessage("Backend Error");
		}

		return res;

	}

}
