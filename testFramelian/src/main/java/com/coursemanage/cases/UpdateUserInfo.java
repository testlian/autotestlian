package com.coursemanage.cases;

import com.coursemanage.config.VariableConfig;
import com.coursemanage.model.UpdateUserCase;
import com.coursemanage.model.User;
import com.coursemanage.utils.CreateSqlSessionUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class UpdateUserInfo {
    @Test(dependsOnGroups = "loginTrue",description = "更新用户信息")
    public void updateUser() throws IOException, InterruptedException {
        SqlSession sqlSession= CreateSqlSessionUtil.getSession();
        UpdateUserCase updateUser=sqlSession.selectOne("updateUser",1);
        System.out.println("updateUserCase:"+updateUser.toString());
        //获取接口返回信息
        int result=getUpdateUser(updateUser);
        //实际结果和预期结果做对比
        User user=sqlSession.selectOne(updateUser.getExpect(),updateUser);
        Assert.assertEquals(1,result);
        Assert.assertEquals(user.getUsername(),updateUser.getUsername());
        Assert.assertEquals(updateUser.getAge(),user.getAge());

    }

    public int getUpdateUser(UpdateUserCase updateuser) throws IOException {
        HttpPost post=new HttpPost(VariableConfig.updateUserUrl);
        JSONObject param=new JSONObject();
        param.put("username",updateuser.getUsername());
        param.put("age",updateuser.getAge());
        param.put("isDelete",updateuser.getIsDelete());
        param.put("id",updateuser.getUserid());
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        post.setHeader("content-type","application/json");
        VariableConfig.client.setCookieStore(VariableConfig.cookieStore);
        HttpResponse response=VariableConfig.client.execute(post);
        String result= EntityUtils.toString(response.getEntity(),"utf-8");
         return Integer.parseInt(result);

    }
    @Test(dependsOnGroups = "loginTrue",description = "删除用户信息")
    public void deleteUser() throws IOException {
        SqlSession sqlSession=CreateSqlSessionUtil.getSession();
        UpdateUserCase deleteUser=sqlSession.selectOne("updateUser",2);
        System.out.println("updateUserCase；"+deleteUser.toString());
        int result=getUpdateUser(deleteUser);
        //实际结果和预期结果做对比
        User user=sqlSession.selectOne(deleteUser.getExpect(),deleteUser);
        Assert.assertEquals(1,result);
        Assert.assertEquals(user.getIsDelete(),deleteUser.getIsDelete());
    }
}
