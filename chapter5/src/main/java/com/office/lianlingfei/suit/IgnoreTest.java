package com.office.lianlingfei.suit;

import org.testng.annotations.Test;

public class IgnoreTest {
    @Test
    public void ignore1(){
        System.out.println("ignore1打印出来");
    }
    @Test(enabled = false)
    public void ignore2(){
        System.out.println("ignore2打印出来");
    }
    @Test(enabled=true)
    public void ignore3(){
        System.out.println("ignore3打印出来");
    }
}