package com.office.lianlingfei.suit;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class SuiteConfig {
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("beforeSuite开始运行。。。。。");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("afterSuite运行结束");
    }
    @BeforeTest
    public void beforeTest(){
        System.out.println("beforeTest开始运行");
    }
    @AfterTest
    public void afterTest(){
        System.out.println("aftertest已经结束");
    }
}

