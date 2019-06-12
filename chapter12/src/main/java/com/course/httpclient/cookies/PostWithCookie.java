package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import sun.security.tools.keytool.Resources;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.List;
public class PostWithCookie {
    private String gUrl;
    private String gUri;
    private String pUri;
    private String getTotalUrl;
    private String putTotalUrl;
    private ResourceBundle bundle;
    private CookieStore cookieStore;
    @BeforeTest
    public void getUrl(){
         bundle=ResourceBundle.getBundle("application",Locale.CHINA);
         gUrl=bundle.getString("get.Url");
         gUri=bundle.getString("cookie.Uri");
         pUri=bundle.getString("test.post.with.cookie");
         getTotalUrl=gUrl+gUri;
         putTotalUrl=gUrl+pUri;

    }
    @Test
    //获取cookie信息
    public void getCookie() throws IOException {
        HttpGet get=new HttpGet(this.getTotalUrl);
        DefaultHttpClient client=new DefaultHttpClient();
        HttpResponse response =client.execute(get);
        this.cookieStore=client.getCookieStore();
        List<Cookie> cookieList=cookieStore.getCookies();
        for(Cookie cookie:cookieList){
            String name=cookie.getName();
            String value=cookie.getValue();
            System.out.println("name="+name+"; value="+value);
        }

    }
    //依赖于上面方法的cookie信息
    @Test(dependsOnMethods = {"getCookie"})
    public void withCookiePost() throws IOException{
        //生成一个post请求
        HttpPost post=new HttpPost(this.putTotalUrl);
        //生成一个client
        DefaultHttpClient client=new DefaultHttpClient();
        //生成一个json对象
        JSONObject para=new JSONObject();
        //向json对象里面加入参数
        para.put("name","xiaomaomi");
        para.put("age","20");
        //将参数信息添加到方法中
        StringEntity entity=new StringEntity(para.toString(),"utf-8");
        post.setEntity(entity);
        //post方法中设置header信息
        post.addHeader("Content-Type","application/json");
        //设置cookie信息
        client.setCookieStore(this.cookieStore);
        //执行post请求并得到响应信息
      HttpResponse response=client.execute(post);
      //获取响应内容
      String result=EntityUtils.toString(response.getEntity());
      System.out.println(result);
      //响应内容转换为json类型
      JSONObject resultJson=new JSONObject(result);
      //获取json中的两个value值
      String s=(String) resultJson.get("xiaomaomi");
      String status=(String) resultJson.get("status");
      //预期结果和实际结果做对比，前面的是预期结果，后面的是实际结果。
        Assert.assertEquals("success",s);
        Assert.assertEquals("1",status);

    }
}
