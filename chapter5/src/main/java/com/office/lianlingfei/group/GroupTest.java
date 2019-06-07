package com.office.lianlingfei.group;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import java.security.acl.Group;

public class GroupTest {
    @Test(groups = "server")
    public void groupOnServer1(){
        System.out.println("groupserver1 打印出来");
    }
    @Test(groups = "server")
    public void groupOnServer2(){
        System.out.println("groupserver2 打印出来");
    }
@Test(groups = "client")
    public void groupOnClient1(){
        System.out.println("groupClient1 打印出来");
    }
    @Test(groups = "client")
    public void groupOnClient2(){
        System.out.println("groupClient2 打印出来");
    }
    @BeforeGroups("server")
    public void beforeGroup(){
        System.out.println("beforeGroup打印出来");
    }
    @AfterGroups("server")
    public void afterGroup(){
        System.out.println("aftergroup 打印出来");
    }

    @BeforeGroups("client")
    public void beforeGroupClient(){
        System.out.println("beforeGroupClient打印出来");
    }
    @AfterGroups("client")
    public void afterGroupClient(){
        System.out.println("aftergroupClient打印出来");
    }
}
