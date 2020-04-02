package com.anz.ws.controller;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxUser;
import com.box.sdk.*;

public class TestBoxAPiConnectivity {
	
	private static final String DEVELOPER_TOKEN = "hjwGK3rCEcnY753ouUWTkXr9004wnp7C";
    private static final int MAX_DEPTH = 1;

    private TestBoxAPiConnectivity() { }

	public static void main(String[] args) {
	
        BoxAPIConnection api = new BoxAPIConnection(DEVELOPER_TOKEN);
        
        BoxUser.Info createdUserInfo = BoxUser.createEnterpriseUser(api, "Amit@box.com", "Amit Gupta");
        System.out.format("Welcome, %s <%s>!\n\n", createdUserInfo.getID(), createdUserInfo.getLogin());

       // BoxUser.Info userInfo = BoxUser.getCurrentUser(api).getInfo();
       // System.out.format("Welcome, %s <%s>!\n\n", userInfo.getName(), userInfo.getLogin());

//        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
//        listFolder(rootFolder, 0);
		

	}
	
	
	private static void listFolder(BoxFolder folder, int depth) {
        for (BoxItem.Info itemInfo : folder) {
            String indent = "";
            for (int i = 0; i < depth; i++) {
                indent += "    ";
            }

            System.out.println(indent + itemInfo.getName());
            if (itemInfo instanceof BoxFolder.Info) {
                BoxFolder childFolder = (BoxFolder) itemInfo.getResource();
                if (depth < MAX_DEPTH) {
                    listFolder(childFolder, depth + 1);
                }
            }
        }
    }
}
