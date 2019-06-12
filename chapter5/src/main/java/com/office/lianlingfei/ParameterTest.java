package com.office.lianlingfei;

import com.beust.jcommander.Parameter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterTest {
    @Test
    @Parameters({"name","age"})
    public void pTest(String name,int age){
        System.out.println("name="+name+"; age="+age);
    }
}
