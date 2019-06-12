package com.office.lianlingfei;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class ParaTest2 {
    @Test(dataProvider = "mt1")
    public void pTest1(String name,int age,String sex){
        System.out.println("执行pTest111111 name="+name+"; age=" +age+";sex="+sex);
    }
    @Test(dataProvider = "mt1")
    public void pTest2(String name,int age,String sex){
        System.out.println("执行pTest2222222 name="+name+"; age=" +age+";sex="+sex);
    }
    @DataProvider(name="mt1")
    public Object[][] paraterMethod(Method method){
        Object[][] result=null;
        if(method.getName().equals("pTest1")){
            result=new  Object[][]{
                    {"lianlingfei",20,"女"},
                    {"wangyinan",30,"男"}
            };
        }else if(method.getName().equals("pTest2")){
            result=new  Object[][]{
                    {"zhangsan",50,"女"},
                    {"lisi",60,"男"},
                    {"wangwu",60,"男"}
            };
        }
        return result;
    }
}
