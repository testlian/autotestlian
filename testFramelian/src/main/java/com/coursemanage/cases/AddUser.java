package com.coursemanage.cases;

import com.coursemanage.config.VariableConfig;
import com.coursemanage.model.User;
import com.coursemanage.model.addUserCase;
import com.coursemanage.utils.CreateSqlSessionUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.coursemanage.config.VariableConfig.cookieStore;

public class AddUser {
    @Test(dependsOnGroups = "loginTrue", description = "增加一个用户")
    public void addUser() throws IOException {
        SqlSession sqlSession = CreateSqlSessionUtil.getSession();
        addUserCase addUserCase = sqlSession.selectOne("addUserCase", 1);
        System.out.println("addUserCase:" + addUserCase.toString());
        //发请求获取结果
        String result=getResult(addUserCase);
        //结果对比
        Assert.assertEquals(addUserCase.getExpect(),result);
    }
    public String getResult(addUserCase addusercase) throws IOException {
        HttpPost httpPost=new HttpPost(VariableConfig.addUserUrl);
        //创建一个json对象
        JSONObject param=new JSONObject();
        param.put("username",addusercase.getUsername());
        param.put("password",addusercase.getPassword());
        param.put("age",addusercase.getAge());
        param.put("sex",addusercase.getSex());
        param.put("permission",addusercase.getPermission());
        param.put("isDelete",addusercase.getIsDelete());
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        httpPost.setEntity(entity);
        //设置头信息
        httpPost.addHeader("content-type","application/json");
        //设置cookie信息
        VariableConfig.client.setCookieStore(cookieStore);
        String result;
        HttpResponse response=VariableConfig.client.execute(httpPost);
        result= EntityUtils.toString(response.getEntity(),"utf-8");
        return  result;
    }
}
