package com.office.lianlingfei;

import org.testng.annotations.Test;

public class UnusualTest {
    @Test(expectedExceptions =RuntimeException.class )
    public void unusualFail(){
        System.out.println("这是一个失败的异常测试");
    }
    @Test(expectedExceptions = RuntimeException.class)
    public void uunusualSuccess(){
        System.out.println("这是一个成功的异常测试");
        throw new RuntimeException();
    }
}
