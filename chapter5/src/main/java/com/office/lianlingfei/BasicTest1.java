package com.office.lianlingfei;

import org.testng.annotations.*;

public class BasicTest1 {
@Test
    public void testCase1()
    {
        System.out.println("hello testcase1");
    }
    @Test
    public void testCase2(){
    System.out.println("hello testcase2");
    }
    @BeforeMethod
public void beforeMethod(){
    System.out.println("beforemethod这个是在方法前运行");
}
@AfterMethod
public void afterMethod(){
    System.out.println("aftermethod这个是在方法之后运行");
}
@BeforeClass
public void beforeClass(){
    System.out.println("beforeclass这个方法是在类执行前先执行");
}
@AfterClass
public void afterClass(){
    System.out.println("afterclass这个方法是在类执行完毕后执行");
}
@BeforeSuite
public void beforeSuite(){
    System.out.println("beforesuite这个是在class运行前运行");
}
@AfterSuite
public void afterSuite(){
    System.out.println("aftersuite这是在class运行后运行");
}
}