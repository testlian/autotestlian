package com.coursemanage.model;

import lombok.Data;

@Data
public class addUserCase {
    private int id;
    private String username;
    private String password;
    private int sex;
    private int age;
    private  int permission;
    private  int isDelete;
    private  String expect;
}
