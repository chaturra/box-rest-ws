package com.anz.ws.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anz.ws.request.model.ServiceRequest;
import com.anz.ws.response.model.CreateFolderResponse;
import com.anz.ws.response.model.ServiceResponse;
import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxCollaboration.Info;
import com.box.sdk.BoxCollaborator;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxUser;
import com.box.sdk.*;

@Service
public class CreateCollaborationService {
	
	@Autowired
	LoadDeveloperTokenService loadToken;

	public ServiceResponse CreateCollaboration(String appUserId, String folderId) throws IOException {
		String accessToken = loadToken.FetchToken();
		ServiceResponse res= new ServiceResponse();
		System.out.println("accessToken: "+accessToken);

		BoxAPIConnection api = new BoxAPIConnection(accessToken);

		
		System.out.println("appUserId : "+appUserId );
		System.out.println("folderId: "+folderId);

		BoxCollaborator user = new BoxUser(api, appUserId);
		BoxFolder folder = new BoxFolder(api, folderId);
		Info collab=folder.collaborate(user, BoxCollaboration.Role.EDITOR);
		System.out.println("API Response: "+collab.getStatus());
		if (null != collab && null!=collab.getStatus()) {
			res.setResultCode("201");
			res.setMessage("accepted");
		}
		else {
			res.setResultCode("500");
			res.setMessage("Backend Error");
		}

		return res;

	}


}
