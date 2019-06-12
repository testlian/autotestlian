package com.office.lianlingfei;

import org.testng.annotations.Test;

public class DependTest {
    @Test(expectedExceptions = RuntimeException.class)
    public void DependTest1(){
        System.out.println("DependTest1 开始运行了");
        throw new RuntimeException();
    }
    @Test(dependsOnMethods = "DependTest1")
    public void DependTest2(){
        System.out.println("DependTest2 开始运行了");
    }
}
