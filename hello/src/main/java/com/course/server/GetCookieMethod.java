package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import sun.awt.SunHints;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//标识要加入bean容器中
@RestController
@Api(value = "/")
public class GetCookieMethod {
    //定义了这个接口的路径是/getCookies，方法是get方法
    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
//HttpServletResponse对象是服务器的响应。这个对象中封装了向客户端发送数据、发送响应头，发送响应状态码的方法。
    @ApiOperation(value = "这个是获取cookie信息",httpMethod = "GET")
    public String getCookie(HttpServletResponse response){
        //生成一个cookie对象
        Cookie cookie=new Cookie("login","true");
        //加入response
       response.addCookie(cookie);
        return "恭喜你获得cookies成功";
    }
    @RequestMapping(value = "/get/with/cookie",method = RequestMethod.GET)
    @ApiOperation(value = "携带cookie信息访问get请求",httpMethod = "GET")
    public String getWithCookie(HttpServletRequest request) {
        //获取客户端访问时候携带的cookies信息
        Cookie[] cookies = request.getCookies();
        //对cookies信息进行判断
        if (Objects.isNull(cookies)) {
            return "请携带cookie信息访问";
        }else {
            for (Cookie cookie : cookies) {
                //判断key和value值都正确才会返回提示成功才可以继续其他操作
                if (cookie.getName().equals("login") && cookie.getValue().equals("true")) {
                    return "恭喜您访问成功";
                }
            }
       return "cookie有误";
        }

    }
    /*携带参数才可以访问的get请求
    第一种方式：key=value&key=value这种形式
    模拟获取商品列表
     */
    @RequestMapping(value = "/get/with/param",method = RequestMethod.GET)
    //start和end接收页面写入的两个参数。
    @ApiOperation(value = "第一个携带参数获取get请求的方法",httpMethod = "GET")
    public Map<String,Integer> getList(@RequestParam Integer start,@RequestParam Integer end){
        //使用集合来存储键值对。
        Map<String,Integer> mapList=new HashMap<String, Integer>();
        //使用put方法向集合中添加数据
        mapList.put("帽子",200);
        mapList.put("口红",300);
        mapList.put("裙子",1000);
        //return返回该集合
        return mapList;
    }
    /*
    第二种方法：ip:port/get/with/param/10/20
     */
    @RequestMapping(value = "/get/with/path/{start}/{end}",method = RequestMethod.GET)
    @ApiOperation(value = "第2个携带参数获取get请求的方法",httpMethod = "GET")
    public Map<String,Integer> getMyList(@PathVariable Integer start,@PathVariable Integer end){
        //使用集合来存储键值对。
        Map<String,Integer> mapList=new HashMap<String, Integer>();
        //使用put方法向集合中添加数据
        mapList.put("帽子",200);
        mapList.put("口红",300);
        mapList.put("裙子",1000);
        //return返回该集合
        return mapList;
    }


}
