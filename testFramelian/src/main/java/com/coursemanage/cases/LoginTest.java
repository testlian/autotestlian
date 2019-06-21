package com.coursemanage.cases;

import com.coursemanage.config.VariableConfig;
import com.coursemanage.model.*;
import com.coursemanage.utils.CreateSqlSessionUtil;
import com.coursemanage.utils.PackageUrl;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {
    @BeforeTest(groups = "loginTrue", description = "测试前准备工作")
    public void beforeTest() {
        VariableConfig.loginUrl = PackageUrl.getUrl(InterfaceName.LOGIN);
        VariableConfig.addUserUrl = PackageUrl.getUrl(InterfaceName.ADDUSER);
        VariableConfig.getUserInfoUrl = PackageUrl.getUrl(InterfaceName.GETUSERINFO);
        VariableConfig.getUserListUrl = PackageUrl.getUrl(InterfaceName.GETUSERLIST);
        VariableConfig.updateUserUrl = PackageUrl.getUrl(InterfaceName.UPDATEUSER);
        VariableConfig.client = new DefaultHttpClient();
    }

    @Test(groups = "loginTrue", description = "登陆成功的测试用例")
    public void loginTrue() throws IOException, InterruptedException {
        SqlSession sqlSession = CreateSqlSessionUtil.getSession();
        //执行loginCase这个id的sql语句，查询
        LoginCase loginCase = sqlSession.selectOne("loginCase",1);
        System.out.println("loginCase:" + loginCase + toString());
        //发送请求得到实际结果
        String result=getResult(loginCase);


        //预期结果和实际结果做对比
        Assert.assertEquals(loginCase.getExpect(),result);
    }
        public String getResult(LoginCase loginCase) throws IOException {
            HttpPost post=new HttpPost(VariableConfig.loginUrl);
            System.out.println("登录连接"+VariableConfig.loginUrl);
            JSONObject param=new JSONObject();
            param.put("username",loginCase.getUsername());
            param.put("password",loginCase.getPassword());
            System.out.println("username:"+loginCase.getUsername());
            System.out.println("password:"+loginCase.getPassword());

            StringEntity entity=new StringEntity(param.toString(),"utf-8");
            post.setEntity(entity);
            post.setHeader("content-type","application/json");
            HttpResponse response=VariableConfig.client.execute(post);
            String result;
            result= EntityUtils.toString(response.getEntity(),"utf-8");
            System.out.println("登录后返回结果为："+result);
            VariableConfig.cookieStore=VariableConfig.client.getCookieStore();
            return result;
        }
    @Test(description = "密码错误，登录失败的测试用例")
    public void loginFalse1() throws IOException {
        SqlSession sqlSession = CreateSqlSessionUtil.getSession();
        //执行loginCase这个id的sql语句，查询
        LoginCase loginCase1 = sqlSession.selectOne("loginCase", 2);
        System.out.println("loginCase:" + loginCase1.toString());
        String result=getResult(loginCase1);

        //预期结果和实际结果做对比
        Assert.assertEquals(loginCase1.getExpect(),result);
    }

    @Test(description = "用户名错误，登录失败的测试用例")
    public void loginFalse2() throws IOException {
        SqlSession sqlSession = CreateSqlSessionUtil.getSession();
        //执行loginCase这个id的sql语句，查询
        LoginCase loginCase2 = sqlSession.selectOne("loginCase", 3);
        System.out.println("loginCase:" + loginCase2.toString());
        String result=getResult(loginCase2);

        //预期结果和实际结果做对比
        Assert.assertEquals(loginCase2.getExpect(),result);
    }
}
