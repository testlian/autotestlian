package com.coursemanage.model;

import lombok.Data;

@Data
public class UpdateUserCase {
    private int id;
    private int userid;
    private String username;
    private int sex;
    private int age;
    private int permission;
    private int isDelete;
    private String expect;
}
