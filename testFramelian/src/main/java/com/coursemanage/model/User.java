package com.coursemanage.model;

import lombok.Data;

@Data
public class User {
    private int id;
    private String username;
    private String password;
    private int age;
    private int sex;
    private int permission;
    private int  isDelete;
@Override
    public String toString(){
        return (
                "{ id:"+id+","+
                        "username:"+username+","+
                        "password:"+password+","+
                        "age:"+age+","+
                        "sex:"+sex+","+
                        "permission:"+permission+","+
                        "isDelete:"+isDelete+"}"
                );
    }
}
