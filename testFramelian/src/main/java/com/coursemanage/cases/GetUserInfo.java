package com.coursemanage.cases;

import com.coursemanage.config.VariableConfig;
import com.coursemanage.model.User;
import com.coursemanage.model.getUserInfoCase;
import com.coursemanage.utils.CreateSqlSessionUtil;
import com.mysql.cj.xdevapi.JsonArray;
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

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class GetUserInfo {
    @Test(dependsOnGroups = "loginTrue", description = "获取某个用户信息")
    public void getUserInfo() throws IOException {
        SqlSession sqlSession = CreateSqlSessionUtil.getSession();
        getUserInfoCase getUserInfo = sqlSession.selectOne("getUserInfoCase", 1);
        System.out.println("getUserInfo；" + getUserInfo.toString());
        //发送请求获取信息
       JSONArray result = getJsonResult(getUserInfo);

        //预期结果和实际结果做比较
        //去数据库查询预期结果。
        List<User> userlist = sqlSession.selectList(getUserInfo.getExpect(), getUserInfo);
        JSONArray jsonuserlist = new JSONArray(userlist);
        Assert.assertEquals(jsonuserlist.length(),result.length());
        for(int i=0;i<jsonuserlist.length();i++){
            JSONObject expect=(JSONObject) jsonuserlist.get(i);
            JSONObject actually=(JSONObject) result.get(i);
            //tostring转成json串
            Assert.assertEquals(expect.toString(),actually.toString());
        }
    }

    //获取接口查询结果
    public JSONArray getJsonResult(getUserInfoCase userInfo) throws IOException {
        HttpPost post = new HttpPost(VariableConfig.getUserInfoUrl);
        JSONObject param = new JSONObject();
        param.put("id", userInfo.getUserid());
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);
        //加入头信息
        post.setHeader("content-type", "application/json");
        VariableConfig.client.setCookieStore(VariableConfig.cookieStore);
        HttpResponse response = VariableConfig.client.execute(post);
        String result;
        result = EntityUtils.toString(response.getEntity());
        JSONArray jsonArray = new JSONArray(result);
        return jsonArray;
    }
}
