package com.coursemanage.utils;

import com.coursemanage.model.InterfaceName;

import java.util.ResourceBundle;

public class PackageUrl {
    private static ResourceBundle bundle = ResourceBundle.getBundle("application");

    public static String getUrl(InterfaceName name) {
        String address = bundle.getString("test.url");
        String uri = "";
        String totalUrl = "";
        if (name == InterfaceName.ADDUSER) {
            uri = bundle.getString("addUser.uri");
        }
        if (name == InterfaceName.GETUSERINFO) {
            uri = bundle.getString("getUserInfo.uri");
        }
        if (name == InterfaceName.GETUSERLIST) {
            uri = bundle.getString("getUserList.uri");
        }
        if (name == InterfaceName.LOGIN) {
            uri = bundle.getString("login.uri");
        }
        if (name == InterfaceName.UPDATEUSER) {
            uri = bundle.getString("updateUserInfo.uri");
        }
        totalUrl = address + uri;
        return totalUrl;
    }

}
