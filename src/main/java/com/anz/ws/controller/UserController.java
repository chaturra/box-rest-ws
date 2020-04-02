package com.anz.ws.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.anz.ws.request.model.*;
import com.anz.ws.response.model.CreateFolderResponse;
import com.anz.ws.response.model.CreateUserApiResponse;
import com.anz.ws.response.model.ServiceResponse;
import com.anz.ws.service.CreateAppUserService;
import com.anz.ws.service.CreateCollaborationService;
import com.anz.ws.service.CreateFolderService;
import com.box.sdk.BoxAPIResponseException;

@RestController
@RequestMapping("/boxapi")
public class UserController {

	@Autowired
	CreateAppUserService serv;

	@Autowired
	CreateFolderService folServ;

	@Autowired
	CreateCollaborationService colServ;

	@GetMapping("/")
	public String health() {
		return "{healthy:true}";
	}

	@PostMapping("/provisionuser")
	public ServiceResponse createProvision(@RequestBody ServiceRequest req) throws Exception {
		CreateUserApiResponse userRes = serv.CreateUser(req);
		ServiceResponse servRes = new ServiceResponse();

		if (userRes.getStatus().equals("201")) {
			String appUserId = userRes.getUserId();
			CreateFolderResponse folRes = folServ.CreateFolder(req);
			if (null != folRes.getFolderId()) {
				servRes = colServ.CreateCollaboration(appUserId, folRes.getFolderId());
			} else {
				servRes.setResultCode(folRes.getStatus());
				servRes.setMessage(folRes.getMessage());
			}

		}

		else {
			servRes.setResultCode(userRes.getStatus());
			servRes.setMessage(userRes.getMessage());
		}
		return servRes;

	}

}
