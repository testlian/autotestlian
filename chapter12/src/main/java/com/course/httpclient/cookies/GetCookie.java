package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.*;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.*;
public class GetCookie {
    private ResourceBundle bundle;
    private String url;
    private String uri;
    private String totalUrl;
    private String cookieUrl;
    private String cookieUri;
    private  CookieStore cookieStore;
    @BeforeTest
    public void getUrl(){
        bundle=ResourceBundle.getBundle("application", Locale.CHINA);
        url=bundle.getString("get.Url");
        uri=bundle.getString("cookie.Uri");
        totalUrl=url+uri;
        cookieUri=bundle.getString("go.with.cookie");
        cookieUrl=url+cookieUri;
    }
    @Test
    public void getCookieContent() throws IOException {
        String result;
        HttpGet get=new HttpGet(this.totalUrl);
        DefaultHttpClient client=new DefaultHttpClient();
       HttpResponse response =client.execute(get);
       result= EntityUtils.toString(response.getEntity(),"utf-8");
       System.out.println(result);
       //获取cookie值
        //创建一个cookie存储类
        this.cookieStore=client.getCookieStore();
        //获取到的cookie内容放到list列表中
        List<Cookie> cookielist=cookieStore.getCookies();
        //读取列表内容
        for(Cookie cookie:cookielist){
            String name=cookie.getName();
            String value=cookie.getValue();
            System.out.println("cookie name="+name+"; cookie value="+value);
        }
    }

//携带cookie访问GoWithCookie接口
       @Test(dependsOnMethods = {"getCookieContent"})
       public void goWithCookie() throws IOException{
        HttpGet httpget=new HttpGet(this.cookieUrl);
        DefaultHttpClient client1=new DefaultHttpClient();
    //设置cookie信息
        client1.setCookieStore(this.cookieStore);
        //执行execute方法返回响应信息
        HttpResponse response1=client1.execute(httpget);
        //获取响应状态码
       int statusCode=response1.getStatusLine().getStatusCode();
       System.out.println("statuscode:"+statusCode);
       //如果状态码是200，那么就打印出响应内容。
       if(statusCode==200){
            String  result1=EntityUtils.toString(response1.getEntity());
            System.out.println(result1);
       }
        }



    }

