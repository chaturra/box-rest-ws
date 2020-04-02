package com.anz.ws.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anz.ws.request.model.ServiceRequest;
import com.anz.ws.response.model.CreateFolderResponse;
import com.anz.ws.response.model.CreateUserApiResponse;
import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxUser;

@Service
public class CreateFolderService {
	@Autowired
	LoadDeveloperTokenService loadToken;

	public CreateFolderResponse CreateFolder(ServiceRequest req) throws IOException {
		String accessToken = loadToken.FetchToken();
		CreateFolderResponse res= new CreateFolderResponse();
		System.out.println("accessToken: "+accessToken);

		BoxAPIConnection api = new BoxAPIConnection(accessToken);

		String parentId = req.getParentFolderId();
		String childFolderName = req.getChildFolderName();
		System.out.println("parentId : "+parentId );
		System.out.println("childFolderName: "+childFolderName);

		BoxFolder parentFolder = new BoxFolder(api, parentId);
		BoxFolder.Info childFolderInfo = parentFolder.createFolder(childFolderName);
		System.out.println("API Response: "+childFolderInfo.getID());
		if (null != childFolderInfo && null!=childFolderInfo.getID()) {
			res.setStatus("201");
			res.setMessage("successfully created");
			res.setFolderId(childFolderInfo.getID());
		}
		else {
			res.setStatus("500");
			res.setMessage("Backend Error");
		}

		return res;

	}

}
