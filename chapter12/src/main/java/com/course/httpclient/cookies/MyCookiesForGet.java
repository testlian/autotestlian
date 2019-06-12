package com.course.httpclient.cookies;

import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGet {
    private String url;
    private String uri;
    private String totalUrl;
    private ResourceBundle bundle;
    @BeforeTest
    //这个方法读取了配置文件的数据，作为一个前置条件为getCookie方法提供数据。
    public void beforeTest(){
        //识别配置文件,properties文件会被自动识别，所以这里只需要写文件名即可
        bundle=ResourceBundle.getBundle("application", Locale.CHINA);
        //获取application中的get.Url数据
        url=bundle.getString("get.Url");
        //获取application中的cookie.Url数据
        uri=bundle.getString("cookie.Uri");
        //拼接成一个完整的url
       totalUrl=url+uri;
    }
    @Test
    public void getCookie() throws IOException {
       String result;
       //创建一个get请求
        HttpGet get=new HttpGet(this.totalUrl);
        //创建一个httpclient对象
        CloseableHttpClient client= HttpClients.createDefault();
        //调用execute方法发送一个请求，返回一个response响应
        CloseableHttpResponse response=client.execute(get);
        //response.getEntity()得到响应内容
        result= EntityUtils.toString(response.getEntity(),"utf-8");
        //打印出响应内容
        System.out.println(result);
        //获取cookies信息

    }

}
