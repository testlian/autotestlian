package com.coursemanage.cases;

import com.coursemanage.config.VariableConfig;
import com.coursemanage.model.User;
import com.coursemanage.model.getUserListCase;
import com.coursemanage.utils.CreateSqlSessionUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GetUserList {
    @Test(dependsOnGroups = "loginTrue",description = "获取用户列表")
    public void getUserList() throws IOException {
        SqlSession sqlSession= CreateSqlSessionUtil.getSession();
        getUserListCase getUserList=sqlSession.selectOne("getUserListCase",1);
        System.out.println("getUserListCase"+getUserList.toString());
        //获取预期结果
        List<User> list=sqlSession.selectList(getUserList.getExpect(),getUserList);
        JSONArray jsonArraylist=new JSONArray(list);
       System.out.println("从数据库查出的数据结果为："+jsonArraylist);

        //获取接口运行实际结果，并与预期结果做对比
        JSONArray result=getResult(getUserList);

        Assert.assertEquals(jsonArraylist.length(),result.length());
        for(int i=0;i<jsonArraylist.length();i++){
            JSONObject expect=(JSONObject) jsonArraylist.get(i);
            JSONObject actually=(JSONObject) result.get(i);
            Assert.assertEquals(expect.toString(),actually.toString());

        }


    }
    public JSONArray getResult(getUserListCase userListCase) throws IOException {
        HttpPost post=new HttpPost(VariableConfig.getUserListUrl);
        JSONObject param=new JSONObject();
        param.put("age",userListCase.getAge());
        param.put("sex",userListCase.getSex());
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        post.setHeader("content-type", "application/json");
        VariableConfig.client.setCookieStore(VariableConfig.cookieStore);
        HttpResponse response=VariableConfig.client.execute(post);
        String result;
        result= EntityUtils.toString(response.getEntity(),"utf-8");
        JSONArray jsonArrayresult=new JSONArray(result);

        return jsonArrayresult;
    }
}
