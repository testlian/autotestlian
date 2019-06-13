package com.course.server;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/")
public class PostCookie {
    private Cookie cookie;
    @ApiOperation(value = "post请求登录后获取cookie信息",httpMethod = "POST")
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String login(HttpServletResponse response,
                        //RequestParam括号里面的userName是用户在浏览器中输入url时候需要携带的参数
                        //对应到方法中就是username这个参数
                        //这里面的value参数可以写也可以不写，如果不写jmeter在访问时候就写username就可以了
                        //如果写了value参数，那么jmeter中访问时候参数名字必须和这里设置的名字
                        @RequestParam(value = "username",required = true) String username,
                        @RequestParam(value = "password",required = true) String password){

        if(username.equals("xiaomaomi") && password.equals("123456")){
            cookie=new Cookie("login","true");
            response.addCookie(cookie);
            return "恭喜你登录成功";
        }else{
            return "用户名或密码错误";
        }
    }
    @ApiOperation(value = "获取用户列表",httpMethod = "POST")
    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    public String getUserList(HttpServletRequest request,@RequestBody User u){
        User user1;
        //获取携带的cookie
        Cookie[] cookies=request.getCookies();
        //对cookie内做判断
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("login") && cookie.getValue().equals("true")
                                && u.getUsername().equals("xiaomaomi")
                                && u.getPassword().equals("123456")){
                //如果cookie正确且用户名和密码均正确，那么就会返回一个User用户。
                user1=new User();
                user1.setAge("20");
                user1.setSex("man");
                user1.setName("wangcai");
                return user1.toString();
            }
        }
        //如果cookie或者参数不正确提示参数不合法
        return "参数不合法";
    }
}
