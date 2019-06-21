package com.coursemanage.config;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.DefaultHttpClient;

public class VariableConfig {
    public static  String addUserUrl;
    public static String loginUrl;
    public static String getUserInfoUrl;
    public static String getUserListUrl;
    public static String updateUserUrl;
    public  static DefaultHttpClient client;
    public  static CookieStore cookieStore;


}
