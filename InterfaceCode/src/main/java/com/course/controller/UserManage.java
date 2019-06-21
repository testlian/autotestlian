package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@Log4j
@Api(value = "v1", description = "用户管理系统")
@RestController
@RequestMapping(value = "v1")
public class UserManage {
    @Autowired
    private SqlSessionTemplate template;

    @ApiOperation(value = "登录接口", httpMethod = "POST")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public boolean login(HttpServletResponse response, @RequestBody User user) {
        int i = template.selectOne("login", user);
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        log.info("查询到的结果是" + i);
        if (i > 0) {
            log.info("username:" + user.getUsername() + ",password" + user.getPassword());
            return true;
        }
        return false;
    }

    @ApiOperation(value = "添加一个用户", httpMethod = "POST")
    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public boolean addUser(HttpServletRequest request, @RequestBody User user) {
        boolean x = verifyCookie(request);
        int result=0;
        if(x==true){
           result=template.insert("adduser",user);
        }
        if(result>0){
            return true;
        }
        return false;
    }

    public boolean verifyCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            return false;
        } else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("login") && cookie.getValue().equals("true")) {
                    log.info("cookie信息正确");
                    return true;
                }
            }
            return false;
        }
    }

    @ApiOperation(value = "获取用户信息（列表信息）",httpMethod = "POST")
    @RequestMapping(value = "/getuserinfo",method = RequestMethod.POST)
    public List<User>  getUserInfo(HttpServletRequest request,@RequestBody User user){
        boolean x=verifyCookie(request);
        List<User> users=null;
        if(x==true){
          users=template.selectList("getuserinfo",user);
        }
       if(users.size()!=0){
           return users;
       }else
       {
           return null;
       }


    }
    @ApiOperation(value = "更新/删除用户信息",httpMethod = "POST")
    @RequestMapping(value = "/updateuser",method = RequestMethod.POST)
    public int updateUser(HttpServletRequest request,@RequestBody User user){
            boolean x=verifyCookie(request);
            int i=0;
            if(x==true){
                i=template.update("updateuser",user);
            }
            return i;
    }
}
