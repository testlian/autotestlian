package com.course.controller;


import com.course.model.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javafx.scene.chart.ValueAxis;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(value = "v1")
@RequestMapping(value = "v1")
public class Demo {
    //这个注解的意思是加载即启动，一加载这个class就启动整个sql对象
    @Autowired
    //获取一个执行sql语句的对象
    private SqlSessionTemplate template;

    @RequestMapping(value = "/getUserCount", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户个数", httpMethod = "GET")
    public int getUserCount() {
        log.info("这是一个log");
        //这里面的getUserCout必须和mysql.xml中的id保持一致
        return template.selectOne("getUserCount");
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ApiOperation(value = "添加一个用户", httpMethod = "POST")
    public int addUser(@RequestBody Student student) {
        int result = template.insert("addUser", student);
        return result;
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    @ApiOperation(value = "更新一个用户数据", httpMethod = "POST")
    public int updateUser(@RequestBody Student student) {
        return template.update("updateUser", student);
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    @ApiOperation(value = "删除一个用户数据", httpMethod = "POST")
    public int deleteUser(@RequestParam int studentno) {
        return template.delete("deleteUser", studentno);
    }
}
